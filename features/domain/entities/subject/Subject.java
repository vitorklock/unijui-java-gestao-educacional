package domain.entities.subject;

public class Subject {
    private static int nextId = 1;
    private int id;
    private String name;

    public Subject(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String novoNome) {
        this.name = novoNome;
    }

    @Override
    public String toString() {
        return name;
    }
}
