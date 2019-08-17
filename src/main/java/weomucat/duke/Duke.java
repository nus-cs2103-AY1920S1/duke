package weomucat.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static weomucat.duke.Parser.PARAMETER_DEFAULT;

public class Duke {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";

    private static final String PARAMETER_AT = "/at";
    private static final String PARAMETER_BY = "/by";

    private static final String SAY_INDENTATION = "\t";
    private static final String SAY_HORIZONTAL_LINE = "============================================================";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Scanner to read stdin
        Scanner scanner = new Scanner(System.in);

        // Greet user
        say("Hello! I'm Duke", "What can I do for you?");

        while (true) {
            // Initialize Parser for new line (user input)
            Parser parser = new Parser(scanner.nextLine());

            // Get command of user input
            String command = parser.nextCommand();

            // Run actions based on which command the user entered.
            switch (command) {

                // Add tasks
                case COMMAND_TODO:
                    HashMap<String, String> parameters = parser.nextParameters();
                    String description = parameters.get(PARAMETER_DEFAULT);

                    addTask(new TodoTask(description));
                    break;

                case COMMAND_EVENT:
                    parameters = parser.nextParameters(PARAMETER_AT);
                    description = parameters.get(PARAMETER_DEFAULT);
                    String at = parameters.get(PARAMETER_AT);

                    addTask(new EventTask(description, at));
                    break;

                case COMMAND_DEADLINE:
                    parameters = parser.nextParameters(PARAMETER_BY);
                    description = parameters.get(PARAMETER_DEFAULT);
                    String by = parameters.get(PARAMETER_BY);

                    addTask(new DeadlineTask(description, by));
                    break;

                // List all tasks.
                case COMMAND_LIST:
                    ArrayList<String> out = new ArrayList<>();
                    out.add("Here are the tasks in your list:");

                    for (int i = 0; i < tasks.size(); i++) {
                        // Get task from tasks
                        Task task = tasks.get(i);

                        // Format task with no. in front
                        out.add(String.format("%d. %s", i + 1, task));
                    }

                    say(out.toArray(new String[0]));
                    break;

                // Mark a task as done.
                case COMMAND_DONE:
                    // Get index of task
                    int i = parser.nextInt() - 1;

                    // Get task from tasks
                    Task task = tasks.get(i);
                    task.setDone(true);

                    say("Nice! I've marked this task as done:", task.toString());
                    break;

                // Terminate the program.
                case COMMAND_BYE:
                    say("Bye. Hope to see you again soon!");
                    return;
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

    private static void addTask(Task task) {
        tasks.add(task);
        say("Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %d task(s) in the list.", tasks.size()));
    }
}
