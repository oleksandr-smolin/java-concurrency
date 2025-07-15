package task.famous.advanced.task19.solution1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Publisher {

    private final List<Subscriber> subscribers;

    public Publisher(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public Publisher() {
        subscribers = new ArrayList<>();
    }

    public void publishMessage() {
        String randomUuid = UUID.randomUUID().toString();
        subscribers.forEach(subscriber -> subscriber.provideMessage(randomUuid));
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
}
