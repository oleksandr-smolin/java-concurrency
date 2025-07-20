package task.famous.advanced.task26.solution1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

public class LoopCrackerTest {

    @Test
    void shouldCrackPassword() throws ExecutionException, InterruptedException {
        Vault vault = new Vault();
        String userName = "alex";
        String pass = "ab";
        vault.registerUser(userName, pass);

        var cracker = new LoopCracker(vault);

        String crackPassword = cracker.crackPassword(userName);

        Assertions.assertEquals(pass, crackPassword);
    }
}
