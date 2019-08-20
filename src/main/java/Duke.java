import java.util.Scanner;


public class Duke {

    public void respond() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            switch (input) {
                case "bye":
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
