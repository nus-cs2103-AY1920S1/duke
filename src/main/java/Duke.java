import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Task> taskList = new ArrayList<>();

    /**
     * If the input is "list", prints the full list of tasks. If the input is
     * "done" or "undo" followed by an integer, marks the task corresponding to
     * the index integer as done or undone respectively. Otherwise, adds the
     * given input to the list of tasks.
     * @param input     User text to be processed.
     */
    private static void process(String input) {
        if (input.equals("list")) {
            DukeFormatter.prettyPrint(taskList);
        } else if (input.substring(0, 4).equals("done")) {
            // Note: "done" must be followed by an integer
            int taskIndex = Integer.parseInt(input.substring(5)); // TODO: handle parseInt error
            if (!isValid(taskIndex)) {
                DukeFormatter.prettyPrint(
                        "Sorry, I couldn't find the task you requested!");
                return;
            }
            Task completedTask = taskList.get(taskIndex - 1); // zero-indexed
            completedTask.markAsDone();
            DukeFormatter.prettyPrint("Nice! I've marked this task as done:\n  "
                    + completedTask.toString());
        } else if (input.substring(0, 4).equals("undo")) {
            // TODO: get rid of duplicated code for "done" and "undo"
            int taskIndex = Integer.parseInt(input.substring(5));
            if (!isValid(taskIndex)) {
                DukeFormatter.prettyPrint(
                        "Sorry, I couldn't find the task you requested!");
                return;
            }
            Task completedTask = taskList.get(taskIndex - 1);
            completedTask.markAsUndone();
            DukeFormatter.prettyPrint("Oh dear. I've marked this task as undone:\n  "
                    + completedTask.toString());
        } else {
            taskList.add(new Task(input));
            DukeFormatter.prettyPrint("added: " + input);
        }
    }

    /**
     * Returns true if a task corresponding to the given index exists and
     * false otherwise.
     * @param taskIndex     Task index to be validated
     * @return              True if the index is valid and false otherwise
     */
    private static boolean isValid(int taskIndex) {
        if (taskIndex < 1 || taskIndex > taskList.size()) {
            return false;
        }
        return true;
    }

    /**
     * Takes in user input and processes it accordingly.
     * @param args  Standard args
     */
    public static void main(String[] args) {
        DukeFormatter.prettyPrint(
                "Hello! I'm Duke\nWhat can I do for you?");
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) { break; }
            process(userInput);
        }
        DukeFormatter.prettyPrint("Bye. Hope to see you again soon!");
    }
}
