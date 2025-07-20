package task.famous.advanced.task26.solution1;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

// @FIXME i'm not working
class ConcurrentBruteForceCracker {

    private final Vault vault;

    private static final char[] CHARSET = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    private static final int MAX_LENGTH = 3;

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(12);

    ConcurrentBruteForceCracker(Vault vault) {
        this.vault = vault;
    }

    String crackPassword(String userName) throws ExecutionException, InterruptedException {

        var futures = IntStream.range(0, MAX_LENGTH).mapToObj(i ->
                CompletableFuture.supplyAsync(() -> {
                    char[] chars = new char[i + 1];
                    Arrays.fill(chars, CHARSET[0]);

                    return bruteForcePass(userName, 0, chars);

                }, EXECUTOR)).toList();

        CompletableFuture<String> result = new CompletableFuture<>();

        futures.forEach(feature -> {
            feature.thenAccept(value -> {
                if (value != null && !result.isDone()) {
                    result.complete(value);
                }
            });
        });

        return result.get();
    }

    private String bruteForcePass(String userName, int localIndex, char[] chars) {
        boolean isLastIndex = localIndex == chars.length - 1;

        var localFuture = new CompletableFuture<String>();

        for (char ch : CHARSET) {
            if (localFuture.isDone()) break;

            chars[localIndex] = ch;

            if (vault.login(userName, String.valueOf(chars))) {
                return String.valueOf(chars);
            }

            if (isLastIndex) continue;

            CompletableFuture.supplyAsync(
                    () -> bruteForcePass(userName, localIndex + 1, Arrays.copyOf(chars, chars.length)), EXECUTOR)
                    .thenAccept(pass -> {
                        if (pass != null && !localFuture.isDone()) {
                            localFuture.complete(pass);
                        }
                    });
        }

        try {
            return localFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
