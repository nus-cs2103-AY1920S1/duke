import java.util.Scanner;

public class Duke {
    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("bye")) {
                break;
            } else {
                System.out.println(command);
            }
        }
        duke.bye();
    }
}
