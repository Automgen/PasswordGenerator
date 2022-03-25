import java.util.Random;

public class PasswordGenerator {

    private static final Random random = new Random();
    private static final char[] symbols = {'#', '@', '!', '?', '&', '+'};

    public static String generatePassword(int length) {
        long oldTime = System.nanoTime();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            switch (random.nextInt(4)) {
                case 0 ->
                    //Number
                        generateNumber(password);
                case 1 ->
                    //Lowercase
                        generateLowercase(password);
                case 2 ->
                    //Uppercase
                        generateUppercase(password);
                case 3 ->
                    //Special character
                        generateSpecialCharacter(password);
                default -> {}
            }
        }
        long newTime = System.nanoTime();
        System.out.println("Password generated in " + (newTime - oldTime) + " ns");
        return password.toString();
    }

    private static void generateNumber(StringBuilder password) {
        char c;
        while(true) {
            c = (char) randomIntInRange('0', '9');
            if (password.isEmpty() || password.charAt(password.length() - 1) != c) {
                password.append(c);
                break;
            }
        }
    }

    private static void generateUppercase(StringBuilder password) {
        char c;
        while(true) {
            c = (char) randomIntInRange('A', 'Z');
            if (password.isEmpty() || password.charAt(password.length() - 1) != c) {
                password.append(c);
                break;
            }
        }
    }

    private static void generateLowercase(StringBuilder password) {
        char c;
        while(true) {
            c = (char) randomIntInRange('a', 'z');
            if (password.isEmpty() || password.charAt(password.length() - 1) != c) {
                password.append(c);
                break;
            }
        }
    }

    private static void generateSpecialCharacter(StringBuilder password) {
        char c;
        while(true) {
            c = symbols[random.nextInt(symbols.length)];
            if (password.isEmpty() || password.charAt(password.length() - 1) != c) {
                password.append(c);
                break;
            }
        }
    }

    /**
     * Retourne un entier entre {@code min} et {@code max} inclus.
     * @param min Borne inférieure incluse
     * @param max Borne supérieure incluse
     * @return Un entier pseudo-alétoire entre {@code min} et {@code max}.
     */
    private static int randomIntInRange(int min, int max) {
        return random.nextInt((max + 1) -min) + min;
    }
}
