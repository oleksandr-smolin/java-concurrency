package task.famous.intermediate.task37;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

class Chat {

    private final UUID chatId;
    private final Set<User> userSet;
    private final ConcurrentLinkedQueue<String> messages;

    Chat(Set<User> userSet) {
        this.chatId = UUID.randomUUID();
        this.userSet = userSet;
        this.messages = new ConcurrentLinkedQueue<>();
    }

    public UUID getChatId() {
        return chatId;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public List<String> getMessages() {
        return List.copyOf(messages);
    }

    boolean sendMessage(String messageBody) {
        return messages.add(messageBody);
    }
}
