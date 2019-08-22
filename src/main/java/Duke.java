import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printMessage(List.of("Hello! I'm Duke", "What can I do for you?"));

        Scanner input = new Scanner(System.in);
        boolean end = false;
        while (!end && input.hasNext()) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                end = true;
                printMessage(List.of("Bye. Hope to see you again soon!"));
            } else {
                printMessage(List.of(command));
            }
        }
    }

    private static void printMessage(List<String> messages) {
        System.out.println("    ____________________________________________________________");
        for (String message : messages) {
            System.out.println("     " + message);
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
