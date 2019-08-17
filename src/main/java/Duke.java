import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println(command);
            System.out.println();
            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

}
