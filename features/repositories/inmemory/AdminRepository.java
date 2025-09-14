package repositories.inmemory;

import entities.user.Admin;
import shared.InMemoryRepository;

public class AdminRepository extends InMemoryRepository<Admin> {
    public AdminRepository() {
        super(Admin::getId);
    }
}
