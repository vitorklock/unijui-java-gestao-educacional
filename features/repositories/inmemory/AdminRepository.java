package repositories.inmemory;

import domain.entities.user.Admin;
import shared.repository.InMemoryRepository;

public class AdminRepository extends InMemoryRepository<Admin> {
    public AdminRepository() {
        super(Admin::getId);
    }
}
