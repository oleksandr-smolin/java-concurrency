package task.famous.advanced.task26.solution1;

import java.util.Arrays;

/**
 * time costly colution
 */
class RecursiveCracker {

    private final Vault vault;

    private static final char[] CHARSET = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    private static final int MAX_LENGTH = 3;

    RecursiveCracker(Vault vault) {
        this.vault = vault;
    }

    String crackPassword(String userName) {
        for (int i = 0; i < MAX_LENGTH; i++) {

            char[] chars = new char[i + 1];
            Arrays.fill(chars, CHARSET[0]);

            String correctPassword = bruteForcePass(userName, 0, chars);

            if (correctPassword != null) return correctPassword;
        }

        return null;
    }

    private String bruteForcePass(String userName, int localIndex, char[] chars) {
        boolean isLastIndex = localIndex == chars.length - 1;

        for (int i = 0; i < CHARSET.length; i++) {
            chars[localIndex] = CHARSET[i];

            if (vault.login(userName, String.valueOf(chars))) {
                return String.valueOf(chars);
            }

            if (isLastIndex) continue;

            String pass = bruteForcePass(userName, localIndex + 1, chars);
            if (pass != null) return pass;

        }

        return null;
    }

}
