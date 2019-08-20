import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Duke {
    boolean exited = false;

    public void respond() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        while (!exited && scanner.hasNextLine()) {
            String input = scanner.nextLine();

            switch (input) {
                case "bye":
                    exited = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                default:
                    System.out.println(input);
            }
        }
    }

    public static void main(String[] args) {
        Duke D1 = new Duke();
        D1.respond();
    }

}
