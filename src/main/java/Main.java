import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Longueur du mot de passe souhaitée (4 min) :");
        int length = 0;
        do {
            try {
                length = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier.");
                sc.next();
            }
        } while (length <= 4);
        sc.close();
        System.out.println("Votre mot de passe : " + PasswordGenerator.generatePassword(length));
    }
}
