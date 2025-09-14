package application.services;

import java.util.List;
import java.util.Objects;

import domain.entities.subject.Subject;
import domain.services.SubjectServiceInterface;
import repositories.inmemory.SubjectRepository;
import shared.exceptions.NotFoundException;
import shared.exceptions.ValidationException;

public class SubjectService implements SubjectServiceInterface {
    private final SubjectRepository subjects;

    public SubjectService(SubjectRepository subjects) {
        this.subjects = Objects.requireNonNull(subjects);
    }

    @Override
    public Subject create(String name) {
        if (name == null || name.isBlank()) throw new ValidationException("Subject name is required");
        return subjects.save(new Subject(name.trim()));
    }

    @Override
    public Subject updateName(int subjectId, String newName) {
        var s = subjects.findById(subjectId).orElseThrow(() -> new NotFoundException("Subject not found: " + subjectId));
        if (newName == null || newName.isBlank()) throw new ValidationException("New name is required");
        s.setName(newName.trim());
        subjects.save(s);
        return s;
    }

    @Override
    public boolean delete(int subjectId) {
        return subjects.deleteById(subjectId);
    }

    @Override
    public Subject get(int subjectId) {
        return subjects.findById(subjectId).orElseThrow(() -> new NotFoundException("Subject not found: " + subjectId));
    }

    @Override
    public List<Subject> listAll() {
        return subjects.findAll();
    }
}