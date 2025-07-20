package task.famous.advanced.task26.solution1;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Vault {

    private final Map<String, UserData> encodedPasswords = new ConcurrentHashMap<>();

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    void registerUser(String userName, String password) {
        byte[] salt = generateSalt();
        String hashedPass = generateHashedPassword(password, salt);
        encodedPasswords.put(userName, new UserData(salt, hashedPass));
    }

    private String generateHashedPassword(String password, byte[] salt) {
        var spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf;
        byte[] hash;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            hash = skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encodeToString(hash);
    }

    public boolean login(String userName, String password) {
        var userData = encodedPasswords.get(userName);
        String hashedPassword = generateHashedPassword(password, userData.salt());

        return userData.hashedPassword().equals(hashedPassword);
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private record UserData(
            byte[] salt,
            String hashedPassword
    ){}
}
