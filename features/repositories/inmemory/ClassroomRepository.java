package repositories.inmemory;

import entities.classroom.Classroom;
import entities.subject.Subject;
import shared.InMemoryRepository;

public class ClassroomRepository extends InMemoryRepository<Classroom> {
    public ClassroomRepository() {
        super(Classroom::getId);
    }
}
