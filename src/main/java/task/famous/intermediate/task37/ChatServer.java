package task.famous.intermediate.task37;

import java.util.*;

class ChatServer {

    private final Map<UUID, Chat> chatMap = new HashMap<>();

    public Map<UUID, Chat> getChatMap() {
        return chatMap;
    }

    boolean sendMessage(Message message) {
        Chat chat;
        if (message.chatId() == null) {
            Set<User> userSet = new HashSet<>();
            userSet.add(message.from());
            userSet.add(message.to());
            chat = new Chat(userSet);
            chatMap.put(chat.getChatId(), chat);
        } else {
            chat = chatMap.get(message.chatId());
        }

        return chat.sendMessage(message.messageBody());
    }
}
