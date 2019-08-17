import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = Client.initialise();

        while (client.read(scanner.nextLine()));

    }
}
