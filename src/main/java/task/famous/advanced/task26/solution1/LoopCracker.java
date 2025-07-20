package task.famous.advanced.task26.solution1;

import java.util.Arrays;

// @FIXME i'm not working
class LoopCracker {

    private final Vault vault;

    private static final char[] CHARSET = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final int MAX_LENGTH = 3;

    LoopCracker (Vault vault) {
        this.vault = vault;
    }

    String crackPassword(String userName) {
        for (int length = 1; length <= MAX_LENGTH; length++) {
            char[] chars = new char[length];
            Arrays.fill(chars, CHARSET[0]);

            while (true) {
                String attempt = String.valueOf(chars);
                if (vault.login(userName, attempt)) {
                    return attempt;
                }

                if (!increment(chars)) {
                    break;
                }
            }
        }

        return null; // No password found
    }

    private boolean increment(char[] chars) {
        int pos = chars.length - 1;

        while (pos >= 0) {
            int index = indexOfChar(chars[pos]);
            if (index < CHARSET.length - 1) {
                chars[pos] = CHARSET[index + 1];
                return true;
            } else {
                chars[pos] = CHARSET[0];
                pos--;
            }
        }

        return false;
    }


    private int indexOfChar(char c) {
        for (int i = 0; i < CHARSET.length; i++) {
            if (CHARSET[i] == c) return i;
        }
        throw new IllegalArgumentException("Invalid char in input: " + c);
    }
}

