import java.util.Arrays;
import java.util.Random;

public class PasswordGenerator {

    private static final char[] majuscules = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] minuscules = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] symboles = {'#', '@', '!', '?', '&', '+'};

    public static String generatePassword(int length) {

        int nbMajuscules = 0;
        int nbMinuscules = 0;
        int nbSymboles = 0;
        int nbChiffres = 0;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {

            switch (randomIntInRange(0, 3)) {
                case 0 -> {
                    // Majuscule
                    result.append(randomMajuscule());
                    nbMajuscules++;
                }
                case 1 -> {
                    // Minuscule
                    result.append(randomMinuscule());
                    nbMinuscules++;
                }
                case 2 -> {
                    // Symbole
                    result.append(randomSymbole());
                    nbSymboles++;
                }
                default -> {
                    // Chiffre
                    result.append(randomChiffre());
                    nbChiffres++;
                }
            }

        }

        if (nbMajuscules == 0) {

            if (nbMinuscules > 1) {
                int indexOfMinsucule = find(minuscules, result.toString());
                result.replace(indexOfMinsucule, indexOfMinsucule, String.valueOf(randomMajuscule()));
            } else if (nbSymboles > 1) {
                int indexOfSymbole = find(symboles, result.toString());
                result.replace(indexOfSymbole, indexOfSymbole, String.valueOf(randomMajuscule()));
            } else {
                int indexOfChiffre = find(new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}, result.toString());
                result.replace(indexOfChiffre, indexOfChiffre, String.valueOf(randomMajuscule()));
            }

        }

        if (nbMinuscules == 0) {

            if (nbMajuscules > 1) {
                int indexOfMajuscule = find(majuscules, result.toString());
                result.replace(indexOfMajuscule, indexOfMajuscule, String.valueOf(randomMinuscule()));
            } else if (nbSymboles > 1) {
                int indexOfSymbole = find(symboles, result.toString());
                result.replace(indexOfSymbole, indexOfSymbole, String.valueOf(randomMinuscule()));
            } else {
                int indexOfChiffre = find(new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}, result.toString());
                result.replace(indexOfChiffre, indexOfChiffre, String.valueOf(randomMinuscule()));
            }

        }

        if (nbSymboles == 0) {

            if (nbMajuscules > 1) {
                int indexOfMajuscule = find(majuscules, result.toString());
                result.replace(indexOfMajuscule, indexOfMajuscule, String.valueOf(randomSymbole()));
            } else if (nbMinuscules > 1) {
                int indexOfMinsucule = find(minuscules, result.toString());
                result.replace(indexOfMinsucule, indexOfMinsucule, String.valueOf(randomSymbole()));
            } else {
                int indexOfChiffre = find(new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}, result.toString());
                result.replace(indexOfChiffre, indexOfChiffre, String.valueOf(randomSymbole()));
            }

        }

        if (nbChiffres == 0) {

            if (nbMajuscules > 1) {
                int indexOfMajuscule = find(majuscules, result.toString());
                result.replace(indexOfMajuscule, indexOfMajuscule, String.valueOf(randomChiffre()));
            } else if (nbMinuscules > 1) {
                int indexOfMinsucule = find(minuscules, result.toString());
                result.replace(indexOfMinsucule, indexOfMinsucule, String.valueOf(randomChiffre()));
            } else {
                int indexOfSymbole = find(symboles, result.toString());
                result.replace(indexOfSymbole, indexOfSymbole, String.valueOf(randomChiffre()));
            }

        }

        return result.toString();
    }

    /**
     * Retourne une majuscule pseudo-aléatoire
     * @return une majuscule pseudo-alétoire
     */
    private static char randomMajuscule() {
        return majuscules[randomIntInRange(0, majuscules.length-1)];
    }

    /**
     * Retourne une minuscule pseudo-aléatoire
     * @return une minuscule pseudo-aléatoire
     */
    private static char randomMinuscule() {
        return minuscules[randomIntInRange(0, minuscules.length-1)];
    }

    /**
     * Retourne un symbole pseudo-aléatoire parmi '#', '@', '!', '?', '&' et '+'
     * @return un symbole pseudo-aléatoire
     */
    private static char randomSymbole() {
        return symboles[randomIntInRange(0, symboles.length-1)];
    }

    /**
     * Retourne un chiffre pseudo-alétoire entre 0 et 9 inclus sous forme de char
     * @return Un chiffre entre 0 et 9 sous forme de char
     */
    private static char randomChiffre() {
        return String.valueOf(randomIntInRange(0, 9)).charAt(0);
    }

    /**
     * Retourne un entier entre {@code min} et {@code max} inclus.
     * @param min Borne inférieure incluse
     * @param max Borne supérieure incluse
     * @return Un entier pseudo-alétoire entre {@code min} et {@code max}.
     */
    private static int randomIntInRange(int min, int max) {

        Random random = new Random();
        return random.nextInt((max+1)-min)+min;

    }

    private static int find(char[] array, String str) {

        int i = 0;
        while (i < str.length() && !contains(array, str.charAt(i))) {
            i++;
        }

        if (i < str.length()) {
            return i;
        } else {
            return -1;
        }

    }

    private static boolean contains(char[] array, char c) {

        int i = 0;
        while (i < array.length && array[i] != c) {
            i++;
        }
        return i < array.length;

    }

}
