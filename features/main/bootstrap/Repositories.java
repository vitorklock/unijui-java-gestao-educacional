package main.bootstrap;

import repositories.inmemory.AdminRepository;
import repositories.inmemory.ClassroomRepository;
import repositories.inmemory.PostRepository;
import repositories.inmemory.StudentRepository;
import repositories.inmemory.SubjectRepository;
import repositories.inmemory.TeacherRepository;

public final class Repositories {
    private final AdminRepository admins;
    private final StudentRepository students;
    private final TeacherRepository teachers;
    private final SubjectRepository subjects;
    private final ClassroomRepository classrooms;
    private final PostRepository posts;

    public Repositories(AdminRepository admins,
                        StudentRepository students,
                        TeacherRepository teachers,
                        SubjectRepository subjects,
                        ClassroomRepository classrooms,
                        PostRepository posts) {
        this.admins = admins;
        this.students = students;
        this.teachers = teachers;
        this.subjects = subjects;
        this.classrooms = classrooms;
        this.posts = posts;
    }

    public AdminRepository admins() { return admins; }
    public StudentRepository students() { return students; }
    public TeacherRepository teachers() { return teachers; }
    public SubjectRepository subjects() { return subjects; }
    public ClassroomRepository classrooms() { return classrooms; }
    public PostRepository posts() { return posts; }
}
