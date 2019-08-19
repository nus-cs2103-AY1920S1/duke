import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);

        Scanner input = new Scanner(System.in);
        String command = input.next();

        while (!command.equals("bye")) {
            System.out.println(command);
            command = input.next();
        }

        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }
}
