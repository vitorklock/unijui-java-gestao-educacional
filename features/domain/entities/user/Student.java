package domain.entities.user;

import domain.entities.user.User.UserRole;

public class Student extends User {
    public Student(String name, String email, String phone) {
        super(name, email, phone);
    }
    
    @Override
    public UserRole role() { return UserRole.STUDENT; }
} 