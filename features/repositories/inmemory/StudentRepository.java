package repositories.inmemory;

import entities.user.Student;
import shared.InMemoryRepository;

public class StudentRepository extends InMemoryRepository<Student> {
    public StudentRepository() {
        super(Student::getId);
    }
}
