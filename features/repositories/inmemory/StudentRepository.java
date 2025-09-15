package repositories.inmemory;

import domain.entities.user.Student;
import shared.repository.InMemoryRepository;

public class StudentRepository extends InMemoryRepository<Student> {
    public StudentRepository() {
        super(Student::getId);
    }
}
