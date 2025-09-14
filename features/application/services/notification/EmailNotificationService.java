package application.services.notification;

import domain.entities.user.User;
import domain.services.NotificationServiceInterface;

public class EmailNotificationService implements NotificationServiceInterface {
       @Override
    public void notify(User to, String message) {
        System.out.println("[EMAIL] -> " + to.getPhone() + " | " + message);
    }
}

