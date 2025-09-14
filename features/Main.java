
import entities.classroom.Classroom;
import entities.mural.Mural;
import entities.post.Post;
import entities.subject.Subject;
import entities.user.Admin;
import entities.user.Student;
import entities.user.Teacher;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Educational Management SPL Demo ===");

        // Users
        Admin admin = new Admin("Admin", "admin@example.com", "0000-0000");
        Teacher teacher = new Teacher("Dr. Smith", "smith@school.com", "1111-1111");
        Student s1 = new Student("John", "john@school.com", "2222-2222");
        Student s2 = new Student("Mary", "mary@school.com", "3333-3333");

        // Subject and classroom
        Subject math = new Subject("Mathematics");
        Classroom c1 = new Classroom(math, teacher);

        // Enrollment
        c1.addStudent(s1);
        c1.addStudent(s2);
        System.out.println("Classroom created: ID " + c1.getId() + " - " + c1.getSubject().getName());
        System.out.println("Teacher: " + c1.getTeacher().getName());
        System.out.println("Students: " + c1.getStudents().size());

        // Teacher posts material to the class mural
        Mural mural = c1.getMural();
        Post p1 = mural.addPost(teacher, "Chapter 1: Sets and Functions");
        System.out.println("Teacher posted a new material: " + p1);

        // Students view mural
        System.out.println("\n-- Student view (John) --");
        for (Post p : mural.getPosts()) {
            System.out.println(p);
            p.getComments().forEach(cm -> System.out.println("   " + cm));
        }

        // John comments
        boolean ok = mural.addComment(p1.getId(), s1, "I have a question about exercise 3.");
        System.out.println("John comment status: " + (ok ? "added" : "post not found"));

        // Teacher replies
        ok = mural.addComment(p1.getId(), teacher, "Sure, John. Which part is unclear?");
        System.out.println("Teacher reply status: " + (ok ? "added" : "post not found"));

        // Show mural again
        System.out.println("\n-- Mural after comments --");
        for (Post p : mural.getPosts()) {
            System.out.println(p);
            p.getComments().forEach(cm -> System.out.println("   " + cm));
        }

        // Teacher sets a grade for John
        c1.setGrade(s1, 8.5);
        System.out.println("\nGrade updated: " + s1.getName() + " -> " + c1.getGrades(s1));

        // Student checks grade
        Double g = c1.getGrades(s1);
        System.out.println(s1.getName() + "'s grade in " + c1.getSubject().getName() + ": " + (g == null ? "N/A" : g));

        System.out.println("\n=== Demo finished ===");
    }
}