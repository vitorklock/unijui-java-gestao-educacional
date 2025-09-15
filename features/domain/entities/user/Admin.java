package domain.entities.user;

import domain.entities.user.User.UserRole;

public class Admin extends User {
    public Admin(String name, String email, String phone) {
        super(name, email, phone);
    }
    
    @Override
    public UserRole role() { return UserRole.ADMIN; }
}