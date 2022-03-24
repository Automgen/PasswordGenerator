import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Longueur du mot de passe souhaitée :");
        int length;
        do {
            length = sc.nextInt();
        } while (length <= 0);

        System.out.println("Votre mot de passe : " + PasswordGenerator.generatePassword(length));

    }

}
