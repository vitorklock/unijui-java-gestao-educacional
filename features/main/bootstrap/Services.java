package main.bootstrap;

import application.services.ClassroomService;
import application.services.SubjectService;
import application.services.notification.CompositeNotificationService;

public final class Services {
    private final SubjectService subjects;
    private final ClassroomService classrooms;

    public Services(SubjectService subjects,
                    ClassroomService classrooms) {
        this.subjects = subjects;
        this.classrooms = classrooms;
    }

    public SubjectService subjects() { return subjects; }
    public ClassroomService classrooms() { return classrooms; }
}
