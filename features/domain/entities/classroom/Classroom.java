package domain.entities.classroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.entities.mural.Mural;
import domain.entities.subject.Subject;
import domain.entities.user.Student;
import domain.entities.user.Teacher;

public class Classroom {
    private static int nextId = 1;
    private int id;
    private Subject subject;
    private Teacher teacher;
    private List<Student> students;
    private Map<Student, Double> grades;
    private Mural mural;

    public Classroom(Subject subject, Teacher teacher) {
        this.id = nextId++;
        this.subject = subject;
        this.teacher = teacher;
        this.students = new ArrayList<Student>();
        this.grades = new HashMap<Student,Double>();
        this.mural = new Mural();
    }

    public int getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Mural getMural() {
        return mural;
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
        	students.add(student);
            grades.put(student, null); 
        }
    }

    public boolean hasStudent(Student student) {
        return students.contains(student);
    }

    public void setGrade(Student student, Double grade) {
        if (students.contains(student)) {
            grades.put(student, grade);
        }
    }

    public Double getGrades(Student student) {
        return grades.get(student);
    }

    public void listarNotas() {
        for (Student a : students) {
            Double n = grades.get(a);
            String notaStr = (n == null ? "N/A" : String.valueOf(n));
            System.out.println("Aluno " + a.getName() + ": " + notaStr);
        }
    }
}