package task.famous.advanced.task19.solution1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class Subscriber {

    private final Queue<String> messages;

    public Subscriber() {
//        this.messages = new ConcurrentLinkedQueue<>();
        this.messages = new LinkedList<>();
    }

    public void provideMessage(String message) {
        ThreadLocalRandom.current().ints(5_000).forEach(__ -> {}); // add some work for thread, don't make its life to easy :)
        messages.offer(message);
    }

    public Queue<String> getMessages() {
        return messages;
    }
}
