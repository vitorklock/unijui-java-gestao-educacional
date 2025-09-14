package repositories.inmemory;

import entities.user.Teacher;
import shared.InMemoryRepository;

public class TeacherRepository extends InMemoryRepository<Teacher> {
    public TeacherRepository() {
        super(Teacher::getId);
    }
}
