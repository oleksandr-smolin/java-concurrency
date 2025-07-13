package task.famous.advanced.task15.solution2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class LoadBalancerTest1 {

    @Test
    void shouldReturnHomePage() {
        LoadBalancer loadBalancer = new LoadBalancer();

        Request request = new Request(1, "/home");

        Response actualResponse = loadBalancer.route(request);

        Assertions.assertEquals("HomePage.html", actualResponse.response());
    }

    @Test
    void shouldReturnResponseFromDifferentServersUsingRoundRobin() {
        LoadBalancer loadBalancer = new LoadBalancer();

        List<Request> requestList = IntStream.range(1, 15).mapToObj(i -> new Request(i, "/home")).toList();

        List<Response> responseList = requestList.stream().map(loadBalancer::route).toList();

        for (int i = 0; i < responseList.size(); i++) {
            Assertions.assertEquals(responseList.get(i).serverId(), i % 3 + 1);
        }
    }

    @Test
    void shouldReturnResponsesInMultiThreadingEnvironment() {
        LoadBalancer loadBalancer = new LoadBalancer();

        int numberOfRequest = 100_101;
        int numberOfServers = 3;

        List<Request> requestList = IntStream.rangeClosed(1, numberOfRequest).mapToObj(i -> new Request(i, "/home")).toList();

        List<CompletableFuture<Response>> futuresList = requestList.stream()
                .map(request -> CompletableFuture.supplyAsync(() -> loadBalancer.route(request))).toList();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futuresList.toArray(CompletableFuture[]::new));
        allFutures.join();

        List<Response> responseList = futuresList.stream()
                .map(CompletableFuture::join)
                .toList();

        Map<Integer, Integer> counterMap = new LinkedHashMap<>();
        IntStream.rangeClosed(1, numberOfServers).forEach(i -> counterMap.put(i, 0));

        responseList.forEach(response -> counterMap.put(response.serverId(), counterMap.get(response.serverId()) + 1));

        int correctionNumber = numberOfRequest % 3;

        for (int i = 1; i <= counterMap.size(); i++) {
            var entry = counterMap.get(i);
            int extraRequests = 0;
            if (correctionNumber > 0) {
                extraRequests++;
                correctionNumber--;
            }
            Assertions.assertEquals(numberOfRequest / 3 + extraRequests, entry.intValue());
        }

    }

}
