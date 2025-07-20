package task.famous.advanced.task26.solution1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecursiveCrackerTest {

    @Test
    void shouldCrackPassword() {
        Vault vault = new Vault();
        String userName = "alex";
        String pass = "abc";
        vault.registerUser(userName, pass);

        var cracker = new RecursiveCracker(vault);

        String crackPassword = cracker.crackPassword(userName);

        Assertions.assertEquals(pass, crackPassword);
    }
}
