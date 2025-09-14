package domain.services;

import java.util.List;

import domain.entities.subject.Subject;

public interface SubjectServiceInterface {
    Subject create(String name);
    Subject updateName(int subjectId, String newName);
    boolean delete(int subjectId);
    Subject get(int subjectId);
    List<Subject> listAll();
}