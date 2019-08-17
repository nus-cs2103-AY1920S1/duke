package weomucat.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
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
        ArrayList<Task> tasks = new ArrayList<>();

        // Greet user
        say("Hello! I'm Duke", "What can I do for you?");

        while (true) {
            // Read user input from stdin.
            String user_input = scanner.nextLine();
            String[] user_input_split = user_input.split(" ");

            // Determine which command the user entered.
            switch (user_input_split[0]) {

                case COMMAND_LIST: // List all tasks.
                    String[] out = new String[tasks.size()];
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        out[i] = String.format("%d. %s", i + 1, task);
                    }
                    say(out);
                    break;

                case COMMAND_DONE: // Mark a task as done.
                    int i = Integer.parseInt(user_input_split[1]) - 1;
                    Task task = tasks.get(i);
                    task.setDone(true);
                    say("Nice! I've marked this task as done:", task.toString());
                    break;

                case COMMAND_BYE: // Terminate the program.
                    say("Bye. Hope to see you again soon!");
                    return;

                default: // By default, add to tasks.
                    task = new Task(user_input);
                    tasks.add(task);
                    say("added: " + task);
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
