package repositories.inmemory;

import entities.subject.Subject;
import shared.InMemoryRepository;

public class SubjectRepository extends InMemoryRepository<Subject> {
    public SubjectRepository() {
        super(Subject::getId);
    }
}
