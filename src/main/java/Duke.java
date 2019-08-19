import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String welcome = "Hello! I'm Duke. What can I do for you?";
        String farewell = "Bye. Hope to see you again soon!";

        print(welcome);
        prompt();

        while (sc.hasNextLine()) {
            String command = sc.next();
            String data = sc.nextLine();

            if (command.equalsIgnoreCase("bye")) break;
            process(command, data);

            prompt();
        }

        print(farewell);
        sc.close();
    }

    private static void process(String command, String data) {
        print(command);
    }

    private static void prompt() {
        System.out.print("> ");
    }

    private static void print(String message) {
        System.out.println(String.format("  %s", message));
    }
}
