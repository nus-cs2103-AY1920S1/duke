package weomucat.duke;

import java.util.Scanner;

public class Duke {
    private static final String COMMAND_BYE = "bye";

    private static final String SAY_INDENTATION = "\t";
    private static final String SAY_HORIZONTAL_LINE = "============================================================";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);

        // Greet user
        say("Hello! I'm Duke", "What can I do for you?");

        while (true) {
            String command = scanner.nextLine();
            String[] command_args = command.split(" ");

            // Determine which command the user entered.
            switch (command_args[0]) {
                case COMMAND_BYE:
                    say("Bye. Hope to see you again soon!");
                    return;

                default: // Echo the command by default.
                    say(command);
                    break;
            }
        }
    }

    private static void say(String... lines) {
        System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
        for (String line : lines) {
            System.out.println(SAY_INDENTATION + line);
        }
        System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
    }
}
