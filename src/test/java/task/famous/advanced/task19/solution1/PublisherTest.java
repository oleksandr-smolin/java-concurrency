package task.famous.advanced.task19.solution1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PublisherTest {


    @Test
    void shouldPublishMessage() {
        Subscriber subscriber = new Subscriber();

        Publisher publisher = new Publisher(List.of(subscriber));
        publisher.publishMessage();

        Assertions.assertFalse(subscriber.getMessages().isEmpty());
    }

    @Test
    void shouldBePossibleToSubscribe() {
        Subscriber subscriber1 = new Subscriber();

        List<Subscriber> subscriberList = new ArrayList<>();
        subscriberList.add(subscriber1);
        Publisher publisher = new Publisher(subscriberList);

        Subscriber subscriber2 = new Subscriber();

        publisher.subscribe(subscriber2);

        publisher.publishMessage();

        Assertions.assertEquals(1, subscriber1.getMessages().size());
        Assertions.assertEquals(subscriber1.getMessages().poll(), subscriber2.getMessages().poll());
    }

    @Test
    void shouldSubscribersGetTheSameMessagesWhenRunSimultaneously() {
        Publisher publisher = new Publisher();

        List<Subscriber> subscriberList = IntStream.range(1, 10_000).mapToObj(i -> new Subscriber()).toList();
        subscriberList.forEach(publisher::subscribe);

        subscriberList.forEach(subscriber -> CompletableFuture.runAsync(() -> publisher.subscribe(subscriber)));

        IntStream.range(0, 1000).forEach(i -> publisher.publishMessage());

        Subscriber firstSubscriber = subscriberList.getFirst();
        List<CompletableFuture<Boolean>> futures = subscriberList.stream()
                .map(subscriber -> CompletableFuture.supplyAsync(
                        () -> subscriber.getMessages().containsAll(firstSubscriber.getMessages())))
                .toList();

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        Set<Boolean> results = futures.stream().map(CompletableFuture::join).collect(Collectors.toSet());

        Assertions.assertEquals(1, results.size());
    }
}
