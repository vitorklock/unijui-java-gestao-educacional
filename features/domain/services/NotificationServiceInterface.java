package domain.services;

import domain.entities.user.User;

public interface NotificationServiceInterface {
    void notify(User to, String message);
}
