import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class PasswordGenerator {

    private PasswordGenerator() {}

    private static final Random random = new SecureRandom();
    private static final char[] symbols = {'#', '@', '!', '?', '&', '+'};

    public static String generatePassword(int length) {
        int nbrOfUppercase = 0, nbrOfLowerCase = 0, nbrOfSymboles = 0, nbrOfNumbers = 0;
        long oldTime = System.nanoTime();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            switch (random.nextInt(4)) {
                case 0 -> {
                    //Number
                    generateNumber(password);
                    nbrOfNumbers++;
                }
                case 1 -> {
                    //Lowercase
                    generateLowercase(password);
                    nbrOfLowerCase++;
                }
                case 2 -> {
                    //Uppercase
                    generateUppercase(password);
                    nbrOfUppercase++;
                }
                case 3 -> {
                    //Special character
                    generateSpecialCharacter(password);
                    nbrOfSymboles++;
                }
            }
        }

        checkUppercase(password, nbrOfSymboles, nbrOfNumbers, nbrOfLowerCase, nbrOfUppercase);
        checkLowercase(password, nbrOfSymboles, nbrOfNumbers, nbrOfLowerCase, nbrOfUppercase);
        checkNumbers(password, nbrOfSymboles, nbrOfNumbers, nbrOfLowerCase, nbrOfUppercase);
        checkSymboles(password, nbrOfSymboles, nbrOfNumbers, nbrOfLowerCase, nbrOfUppercase);

        long newTime = System.nanoTime();
        System.out.println("Password generated in " + (newTime - oldTime) + " ns");
        return password.toString();
    }

    private static void checkUppercase(StringBuilder password, int nbrOfSymboles, int nbrOfNumbers, int nbrOfLowerCase, int nbrOfUppercase) {
        if(nbrOfUppercase == 0) {
            Stream<Character> stream = password.toString().chars().mapToObj(c -> (char) c);
            if(nbrOfLowerCase > 1) {
                stream.filter(Character::isLowerCase).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), (char) randomIntInRange('A', 'Z')));
            } else if(nbrOfNumbers > 1) {
                stream.filter(Character::isDigit).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), (char) randomIntInRange('A', 'Z')));
            } else if(nbrOfSymboles > 1) {
                stream.filter(c -> Arrays.binarySearch(symbols, c) < 0).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), (char) randomIntInRange('A', 'Z')));
            }
        }
    }

    private static void checkLowercase(StringBuilder password, int nbrOfSymboles, int nbrOfNumbers, int nbrOfLowerCase, int nbrOfUppercase) {
        if(nbrOfLowerCase == 0) {
            Stream<Character> stream = password.toString().chars().mapToObj(c -> (char) c);
            if(nbrOfUppercase > 1) {
                stream.filter(Character::isUpperCase).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), (char) randomIntInRange('a', 'z')));
            } else if(nbrOfNumbers > 1) {
                stream.filter(Character::isDigit).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), (char) randomIntInRange('a', 'z')));
            } else if(nbrOfSymboles > 1) {
                stream.filter(c -> Arrays.binarySearch(symbols, c) < 0).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), (char) randomIntInRange('a', 'z')));
            }
        }
    }

    private static void checkNumbers(StringBuilder password, int nbrOfSymboles, int nbrOfNumbers, int nbrOfLowerCase, int nbrOfUppercase) {
        if(nbrOfNumbers == 0) {
            Stream<Character> stream = password.toString().chars().mapToObj(c -> (char) c);
            if(nbrOfLowerCase > 1) {
                stream.filter(Character::isLowerCase).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), (char) randomIntInRange('0', '9')));
            } else if(nbrOfUppercase > 1) {
                stream.filter(Character::isUpperCase).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), (char) randomIntInRange('0', '9')));
            } else if(nbrOfSymboles > 1) {
                stream.filter(c -> Arrays.binarySearch(symbols, c) < 0).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), (char) randomIntInRange('0', '9')));
            }
        }
    }

    private static void checkSymboles(StringBuilder password, int nbrOfSymboles, int nbrOfNumbers, int nbrOfLowerCase, int nbrOfUppercase) {
        if(nbrOfSymboles == 0) {
            Stream<Character> stream = password.toString().chars().mapToObj(c -> (char) c);
            if(nbrOfLowerCase > 1) {
                stream.filter(Character::isLowerCase).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), symbols[random.nextInt(symbols.length)]));
            } else if(nbrOfUppercase > 1) {
                stream.filter(Character::isUpperCase).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), symbols[random.nextInt(symbols.length)]));
            } else if(nbrOfNumbers > 1) {
                stream.filter(Character::isDigit).findFirst().ifPresent(c -> password.setCharAt(password.indexOf(String.valueOf(c)), symbols[random.nextInt(symbols.length)]));
            }
        }
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
        return random.nextInt(min, max + 1);
    }
}