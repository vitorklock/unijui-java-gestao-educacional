package application.services.notification;

import java.util.ArrayList;
import java.util.List;

import domain.entities.user.User;
import domain.services.NotificationServiceInterface;

public class CompositeNotificationService implements NotificationServiceInterface {
    private final List<NotificationServiceInterface> channels = new ArrayList<>();

    public CompositeNotificationService add(NotificationServiceInterface channel) {
        if (channel != null) channels.add(channel);
        return this;
    }

    public boolean isEmpty() { return channels.isEmpty(); }

    @Override
    public void notify(User to, String message) {
        for (NotificationServiceInterface c : channels) {
            c.notify(to, message);
        }
    }
}
