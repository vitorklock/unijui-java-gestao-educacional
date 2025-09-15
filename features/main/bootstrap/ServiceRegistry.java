package main.bootstrap;

import application.services.ClassroomService;
import application.services.SubjectService;
import application.services.notification.CompositeNotificationService;
import repositories.inmemory.AdminRepository;
import repositories.inmemory.ClassroomRepository;
import repositories.inmemory.PostRepository;
import repositories.inmemory.StudentRepository;
import repositories.inmemory.SubjectRepository;
import repositories.inmemory.TeacherRepository;

public record ServiceRegistry(
        AdminRepository admins,
        StudentRepository students,
        TeacherRepository teachers,
        SubjectRepository subjects,
        ClassroomRepository classrooms,
        PostRepository posts,
        SubjectService subjectService,
        ClassroomService classroomService,
        CompositeNotificationService notifications
) {}
