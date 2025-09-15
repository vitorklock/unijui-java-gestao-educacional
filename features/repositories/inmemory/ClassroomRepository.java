package repositories.inmemory;

import domain.entities.classroom.Classroom;
import domain.entities.subject.Subject;
import shared.repository.InMemoryRepository;

public class ClassroomRepository extends InMemoryRepository<Classroom> {
    public ClassroomRepository() {
        super(Classroom::getId);
    }
}
