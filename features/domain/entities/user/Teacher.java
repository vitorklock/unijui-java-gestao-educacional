package domain.entities.user;

public class Teacher extends User {
    public Teacher(String name, String email, String phone) {
        super(name, email, phone);
    }
    
    @Override
    public UserRole role() { return UserRole.TEACHER; }
} 