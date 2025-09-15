package repositories.inmemory;

import domain.entities.subject.Subject;
import shared.repository.InMemoryRepository;

public class SubjectRepository extends InMemoryRepository<Subject> {
    public SubjectRepository() {
        super(Subject::getId);
    }
}
