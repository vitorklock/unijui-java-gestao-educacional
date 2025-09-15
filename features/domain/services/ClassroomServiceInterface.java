package domain.services;

import java.util.List;

import domain.entities.classroom.Classroom;
import domain.entities.post.Post;
import domain.entities.subject.Subject;
import domain.entities.user.Student;
import domain.entities.user.Teacher;

public interface ClassroomServiceInterface {
    Classroom createClassroom(int subjectId, int teacherId);
    Classroom get(int classroomId);

    void enrollStudent(int classroomId, int studentId);

    Post postMaterial(int classroomId, int authorTeacherId, String content);
    void commentOnPost(int classroomId, int authorUserId, int postId, String text);
    List<Post> listPosts(int classroomId);

    void assignGrade(int classroomId, int teacherId, int studentId, double grade);
    Double getGrade(int classroomId, int studentId);

    List<Classroom> listByTeacher(int teacherId);
    List<Classroom> listByStudent(int studentId);

    List<Subject> listAllSubjects();
    List<Student> listAllStudents();
    List<Teacher> listAllTeachers();
}
