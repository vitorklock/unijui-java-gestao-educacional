package application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import domain.entities.classroom.Classroom;
import domain.entities.post.Post;
import domain.entities.subject.Subject;
import domain.entities.user.Student;
import domain.entities.user.Teacher;
import domain.entities.user.User;
import domain.services.ClassroomServiceInterface;
import domain.services.NotificationServiceInterface;
import repositories.inmemory.ClassroomRepository;
import repositories.inmemory.PostRepository;
import repositories.inmemory.StudentRepository;
import repositories.inmemory.SubjectRepository;
import repositories.inmemory.TeacherRepository;
import shared.exceptions.ForbiddenOperationException;
import shared.exceptions.NotFoundException;
import shared.exceptions.ValidationException;

public class ClassroomService implements ClassroomServiceInterface {
	private final ClassroomRepository classrooms;
	private final SubjectRepository subjects;
	private final StudentRepository students;
	private final TeacherRepository teachers;
	private final PostRepository posts;
	private final NotificationServiceInterface notifications;

	public ClassroomService(ClassroomRepository classrooms, SubjectRepository subjects, StudentRepository students,
			TeacherRepository teachers, PostRepository posts, NotificationServiceInterface notifications) {
		this.classrooms = Objects.requireNonNull(classrooms);
		this.subjects = Objects.requireNonNull(subjects);
		this.students = Objects.requireNonNull(students);
		this.teachers = Objects.requireNonNull(teachers);
		this.posts = Objects.requireNonNull(posts);
		this.notifications = Objects.requireNonNull(notifications);
	}

	@Override
	public Classroom createClassroom(int subjectId, int teacherId) {
		Subject subject = subjects.findById(subjectId)
				.orElseThrow(() -> new NotFoundException("Subject not found: " + subjectId));

		Teacher teacher = teachers.findById(teacherId)
				.orElseThrow(() -> new NotFoundException("Teacher not found: " + teacherId));

		Classroom c = new Classroom(subject, teacher);

		return classrooms.save(c);
	}

	@Override
	public Classroom get(int classroomId) {
		return classrooms.findById(classroomId)
				.orElseThrow(() -> new NotFoundException("Classroom not found: " + classroomId));
	}

	@Override
	public void enrollStudent(int classroomId, int studentId) {
		Classroom c = get(classroomId);

		Student s = students.findById(studentId)
				.orElseThrow(() -> new NotFoundException("Student not found: " + studentId));

		c.addStudent(s);
		classrooms.save(c);

		notifications.notify(s, "You have been enrolled in " + c.getSubject().getName());
	}

	@Override
	public Post postMaterial(int classroomId, int authorTeacherId, String content) {

		if (content == null || content.isBlank())
			throw new ValidationException("Content is required");

		Classroom c = get(classroomId);
		Teacher teacher = teachers.findById(authorTeacherId)
				.orElseThrow(() -> new NotFoundException("Teacher not found: " + authorTeacherId));

		if (!c.getTeacher().equals(teacher))
			throw new ForbiddenOperationException("Only the class teacher can post material");

		Post p = c.getMural().addPost(teacher, content.trim());
		classrooms.save(c);
		posts.save(p);

		c.getStudents().forEach(stu -> notifications.notify(stu, "New material in " + c.getSubject().getName()));
		return p;
	}

	@Override
	public void commentOnPost(int classroomId, int authorUserId, int postId, String text) {

		if (text == null || text.isBlank())
			throw new ValidationException("Comment text is required");

		Classroom c = get(classroomId);
		User author = students.findById(authorUserId).map(s -> (User) s)
				.or(() -> teachers.findById(authorUserId).map(t -> (User) t))
				.orElseThrow(() -> new NotFoundException("User not found: " + authorUserId));

		boolean ok = c.getMural().addComment(postId, author, text.trim());

		if (!ok)
			throw new NotFoundException("Post not found: " + postId);
		
		classrooms.save(c);
	}

	@Override
	public ArrayList<Post> listPosts(int classroomId) {
		return new ArrayList<Post>(get(classroomId).getMural().getPosts());
	}

	@Override
	public void assignGrade(int classroomId, int teacherId, int studentId, double grade) {
		
		if (grade < 0.0 || grade > 10.0)
			throw new ValidationException("Grade must be between 0 and 10");
		
		Classroom c = get(classroomId);
		Teacher t = teachers.findById(teacherId)
				.orElseThrow(() -> new NotFoundException("Teacher not found: " + teacherId));
		Student s = students.findById(studentId)
				.orElseThrow(() -> new NotFoundException("Student not found: " + studentId));

		if (!c.getTeacher().equals(t))
			throw new ForbiddenOperationException("Only the class teacher can assign grades");
		if (!c.hasStudent(s))
			throw new ForbiddenOperationException("Student is not enrolled in this classroom");

		c.setGrade(s, grade);
		classrooms.save(c);
		notifications.notify(s, "Your grade in " + c.getSubject().getName() + " is now " + grade);
	}

	@Override
	public Double getGrade(int classroomId, int studentId) {
		Classroom c = get(classroomId);
		
		Student s = students.findById(studentId)
				.orElseThrow(() -> new NotFoundException("Student not found: " + studentId));
		
		return c.getGrades(s);
	}

	@Override
	public List<Classroom> listByTeacher(int teacherId) {
		var t = teachers.findById(teacherId)
				.orElseThrow(() -> new NotFoundException("Teacher not found: " + teacherId));
		
		return classrooms.findAll().stream().filter(c -> c.getTeacher().equals(t)).toList();
	}

	@Override
	public List<Classroom> listByStudent(int studentId) {
		var s = students.findById(studentId)
				.orElseThrow(() -> new NotFoundException("Student not found: " + studentId));
		
		return classrooms.findAll().stream().filter(c -> c.hasStudent(s)).toList();
	}

	@Override
	public List<Subject> listAllSubjects() {
		return subjects.findAll();
	}

	@Override
	public List<Student> listAllStudents() {
		return students.findAll();
	}

	@Override
	public List<Teacher> listAllTeachers() {
		return teachers.findAll();
	}
}
