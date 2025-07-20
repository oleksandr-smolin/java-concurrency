package task.famous.advanced.task26.solution1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VaultTest {

    @Test
    void shouldRegisterUserAndLoginSuccessfully() {
        Vault vault = new Vault();

        String userName = "myUser";
        String password = UUID.randomUUID().toString();

        vault.registerUser(userName, password);

        Assertions.assertTrue(vault.login(userName, password));
    }

    @Test
    void shouldRejectLoginWithWrongPassword() {
        Vault vault = new Vault();

        String userName = "myUser";
        String password = UUID.randomUUID().toString();

        vault.registerUser(userName, password);

        Assertions.assertFalse(vault.login(userName, "InvalidPassword"));
    }

    @Test
    void shouldHandleConcurrentRegistrationsCorrectly() {
        Vault vault = new Vault();

        List<CompletableFuture<Boolean>> futures = IntStream.range(0, 1000).mapToObj(String::valueOf)
                .map(indexAsString ->
                        CompletableFuture.runAsync(() -> vault.registerUser(indexAsString, indexAsString))
                                .thenComposeAsync(v ->
                                        CompletableFuture.supplyAsync(() -> vault.login(indexAsString, indexAsString))))
                .toList();

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        Set<Boolean> resultSet = futures.stream().map(CompletableFuture::join).collect(Collectors.toSet());
        Assertions.assertTrue(resultSet.contains(true));
        Assertions.assertEquals(1, resultSet.size());
    }

}
