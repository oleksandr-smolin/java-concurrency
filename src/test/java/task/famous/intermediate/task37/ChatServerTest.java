package task.famous.intermediate.task37;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Iterables;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChatServerTest {

    @Test
    void givenUsers_shouldSendMessageEachOther() {
        User john = new User(1, "John");
        User simon = new User(2, "Simon");

        ChatServer chatServer = new ChatServer();

        String messageBody = "Hello " + simon.username();
        boolean isMessageSent = chatServer.sendMessage(new Message(null, john, simon, messageBody));

        Assertions.assertTrue(isMessageSent);
        Collection<Chat> chatList = chatServer.getChatMap().values();
        Assertions.assertEquals(1, chatList.size());

        Assertions.assertEquals(messageBody, chatList.toArray(Chat[]::new)[0].getMessages().get(0));
    }

    @Test
    void givenUsers_shouldSendMessagesSimultaneouslyCopy1() {
        User john = new User(1, "John");
        User simon = new User(2, "Simon");

        int numberOfMessagesFromOnUser = 5000;

        ChatServer chatServer = new ChatServer();
        chatServer.sendMessage(new Message(null, john, simon, "Hello " + simon.username()));
        UUID uuid = Iterables.firstOf(chatServer.getChatMap().keySet());

        ExecutorService es = Executors.newFixedThreadPool(12);

        List<Callable<Void>> tasks = IntStream.range(0, numberOfMessagesFromOnUser)
                .mapToObj(i -> List.of(
                        new Message(uuid, john, simon, "hi again. it is %d timed when you said that".formatted(i)),
                                        new Message(uuid, simon, john, "hi again. it is %d timed when you said that".formatted(i))))
                .flatMap(Collection::stream)
                .map(message -> (Callable<Void>) () -> {
                    chatServer.sendMessage(message);
                    return null;
                })
                .toList();

        try {
            es.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        int messagesNumber = Iterables.firstOf(chatServer.getChatMap().values()).getMessages().size();

        Assertions.assertEquals(numberOfMessagesFromOnUser * 2 + 1, messagesNumber);
    }

    @Test
    void givenUsers_shouldSendMessagesSimultaneouslyCopy2() {
        User john = new User(1, "John");
        User simon = new User(2, "Simon");

        int numberOfMessagesFromOnUser = 5000;

        ChatServer chatServer = new ChatServer();
        chatServer.sendMessage(new Message(null, john, simon, "Hello " + simon.username()));
        UUID uuid = Iterables.firstOf(chatServer.getChatMap().keySet());

        ExecutorService es = Executors.newFixedThreadPool(12);

        List<Message> messages = IntStream.range(0, numberOfMessagesFromOnUser)
                .mapToObj(i -> List.of(
                        new Message(uuid, john, simon, "hi again. it is %d timed when you said that".formatted(i)),
                        new Message(uuid, simon, john, "hi again. it is %d timed when you said that".formatted(i))))
                .flatMap(Collection::stream)
                .toList();

        messages.forEach(message -> es.submit(() -> chatServer.sendMessage(message)));

        es.shutdown();

        try {
            es.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            es.shutdownNow();
        }

        int messagesNumber = Iterables.firstOf(chatServer.getChatMap().values()).getMessages().size();

        Assertions.assertEquals(numberOfMessagesFromOnUser * 2 + 1, messagesNumber);
    }

    @Test
    void givenUsers_shouldSendMessagesSimultaneouslyCopy3() {
        User john = new User(1, "John");
        User simon = new User(2, "Simon");

        int numberOfMessagesFromOnUser = 5000;

        ChatServer chatServer = new ChatServer();
        chatServer.sendMessage(new Message(null, john, simon, "Hello " + simon.username()));
        UUID uuid = Iterables.firstOf(chatServer.getChatMap().keySet());

        IntStream.range(0, numberOfMessagesFromOnUser).mapToObj(i -> List.of(
                        new Message(uuid, john, simon, "hi again. it is %d timed when you said that".formatted(i)),
                        new Message(uuid, simon, john, "hi again. it is %d timed when you said that".formatted(i))))
                .flatMap(Collection::stream)
                .forEach(message -> CompletableFuture.runAsync(() -> chatServer.sendMessage(message)));

        int messagesNumber = Iterables.firstOf(chatServer.getChatMap().values()).getMessages().size();

        Assertions.assertEquals(numberOfMessagesFromOnUser * 2 + 1, messagesNumber);
    }
}
