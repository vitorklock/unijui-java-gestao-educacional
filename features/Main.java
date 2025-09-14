
import application.services.ClassroomService;
import application.services.SubjectService;
import application.services.notification.CompositeNotificationService;
import application.services.notification.EmailNotificationService;
import application.services.notification.WhatsappNotificationService;
import domain.entities.classroom.Classroom;
import domain.entities.post.Post;
import domain.entities.subject.Subject;
import domain.entities.user.Admin;
import domain.entities.user.Student;
import domain.entities.user.Teacher;
import repositories.inmemory.AdminRepository;
import repositories.inmemory.ClassroomRepository;
import repositories.inmemory.PostRepository;
import repositories.inmemory.StudentRepository;
import repositories.inmemory.SubjectRepository;
import repositories.inmemory.TeacherRepository;

public class Main {
	public static void main(String[] args) {
		System.out.println("=== Educational Management SPL - Services Demo ===");

		// ---------- Repositories ----------
		var adminsRepo = new AdminRepository();
		var studentsRepo = new StudentRepository();
		var teachersRepo = new TeacherRepository();
		var subjectsRepo = new SubjectRepository();
		var classroomsRepo = new ClassroomRepository();
		var postsRepo = new PostRepository();

		// ---------- Notifications (FeatureIDE-friendly) ----------
		// Composite holds the channels you include per feature (Email/WhatsApp/etc.)
		var notifications = new CompositeNotificationService();

		// For ONLINE variant, you typically add concrete channels here (only if feature
		// selected)
		notifications.add(new EmailNotificationService()); // implements NotificationServiceInterface
//		 notifications.add(new WhatsappNotificationService()); // implements
		// NotificationServiceInterface

		// For PRESENTIAL variant, leave it empty (no notifications)
		// if (notifications.isEmpty()) System.out.println("(Notifications disabled in
		// this variant)");

		// ---------- Services ----------
		SubjectService subjectService = new SubjectService(subjectsRepo);
		ClassroomService classroomService = new ClassroomService(classroomsRepo, subjectsRepo, studentsRepo,
				teachersRepo, postsRepo, notifications);

		// ---------- Seed data (Admin/Teacher/Students/Subject) ----------
		var admin = adminsRepo.save(new Admin("Admin", "admin@example.com", "0000-0000"));
		var teacher = teachersRepo.save(new Teacher("Dr. Smith", "smith@school.com", "1111-1111"));
		var student1 = studentsRepo.save(new Student("John", "john@school.com", "2222-2222"));
		var student2 = studentsRepo.save(new Student("Mary", "mary@school.com", "3333-3333"));

		Subject math = subjectService.create("Mathematics");

		// ---------- Create classroom and enroll students ----------
		Classroom c1 = classroomService.createClassroom(math.getId(), teacher.getId());
		System.out.println("Created classroom: ID " + c1.getId() + " | Subject: " + c1.getSubject().getName()
				+ " | Teacher: " + c1.getTeacher().getName());

		classroomService.enrollStudent(c1.getId(), student1.getId());
		classroomService.enrollStudent(c1.getId(), student2.getId());

		// ---------- Teacher posts material ----------
		Post p1 = classroomService.postMaterial(c1.getId(), teacher.getId(), "Chapter 1: Sets and Functions");
		System.out.println("Posted material: " + p1);

		// ---------- List posts ----------
		System.out.println("\n-- Mural Posts --");
		classroomService.listPosts(c1.getId()).forEach(p -> {
			System.out.println(p);
			p.getComments().forEach(cm -> System.out.println("   " + cm));
		});

		// ---------- John comments on the post ----------
		classroomService.commentOnPost(c1.getId(), student1.getId(), p1.getId(), "I have a question about exercise 3.");

		// ---------- Teacher replies ----------
		classroomService.commentOnPost(c1.getId(), teacher.getId(), p1.getId(), "Sure, John. Which part is unclear?");

		// ---------- Show mural again ----------
		System.out.println("\n-- Mural After Comments --");
		classroomService.listPosts(c1.getId()).forEach(p -> {
			System.out.println(p);
			p.getComments().forEach(cm -> System.out.println("   " + cm));
		});

		// ---------- Assign and read a grade ----------
		classroomService.assignGrade(c1.getId(), teacher.getId(), student1.getId(), 8.5);
		Double grade = classroomService.getGrade(c1.getId(), student1.getId());
		System.out.println("\nGrade updated: " + student1.getName() + " -> " + grade);

		System.out.println("\n=== Demo finished ===");
	}
}