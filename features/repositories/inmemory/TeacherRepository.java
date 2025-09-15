package repositories.inmemory;

import domain.entities.user.Teacher;
import shared.repository.InMemoryRepository;

public class TeacherRepository extends InMemoryRepository<Teacher> {
    public TeacherRepository() {
        super(Teacher::getId);
    }
}
