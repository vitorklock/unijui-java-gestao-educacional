package main.bootstrap;

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

public class AppBootstrap {

	private AppBootstrap() {
	}

	public static AppContext init() {

		// --- Repositories ---
		var adminsRepo = new AdminRepository();
		var studentsRepo = new StudentRepository();
		var teachersRepo = new TeacherRepository();
		var subjectsRepo = new SubjectRepository();
		var classroomsRepo = new ClassroomRepository();
		var postsRepo = new PostRepository();

		var repos = new Repositories(adminsRepo, studentsRepo, teachersRepo, subjectsRepo, classroomsRepo, postsRepo);

		// --- Notifications (FeatureIDE-friendly) ---
		var notifications = new CompositeNotificationService();
		
		
		
		/*#if Email */
		notifications.add(new EmailNotificationService());
	    /*#endif*/
		
		/*#if Whatsapp */
		notifications.add(new WhatsappNotificationService());
	    /*#endif*/

		// --- Services ---
		SubjectService subjectService = new SubjectService(subjectsRepo);
		ClassroomService classroomService = new ClassroomService(classroomsRepo, subjectsRepo, studentsRepo,
				teachersRepo, postsRepo, notifications);

		var services = new Services(subjectService, classroomService);

		// ---------- Users ----------
		Admin admin = adminsRepo.save(new Admin("Admin", "admin@example.com", "0000-0000"));
		Teacher teacher = teachersRepo.save(new Teacher("Lori", "smith@school.com", "1111-1111"));
		Student student1 = studentsRepo.save(new Student("João", "joao@unijui.com", "2222-2222"));
		Student student2 = studentsRepo.save(new Student("Maria", "maria@unijui.com", "3333-3333"));

		Subject math = subjectService.create("Matemática");
		Classroom c1 = classroomService.createClassroom(math.getId(), teacher.getId());

		classroomService.enrollStudent(c1.getId(), student1.getId());
		classroomService.enrollStudent(c1.getId(), student2.getId());
		Post post1 = classroomService.postMaterial(c1.getId(), teacher.getId(), "Capítulo 1: Domínios e Funções");
		
		classroomService.commentOnPost(c1.getId(), student1.getId(), post1.getId(), "Professor, terá desafio hoje?");
		
		Classroom c2 = classroomService.createClassroom(math.getId(), teacher.getId());
		
		classroomService.enrollStudent(c2.getId(), student1.getId());
		classroomService.postMaterial(c2.getId(), teacher.getId(), "Capítulo 1: Ordem logaritmica");
		
		
		// --- Hand off everything to the UI via a context ---
		return new AppContext(repos, services);
	}

}
