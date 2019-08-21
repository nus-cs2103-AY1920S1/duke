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
     * @param input     User text to be processed. Input can take one of the
     *                  following forms:
     *                  1. "list"
     *                  2. "done [taskIndex]"
     *                  3. "undo [taskIndex]"
     *                  4. "todo [description]"
     *                  5. "deadline [description] /by [time]"
     *                  6. "event [description] /at [time]"
     *                  7. "[taskDescription]" (default: treat as Todo)
     */
    private static void process(String input) {
        if (input.equalsIgnoreCase("list")) {
            DukeFormatter.prettyPrint(taskList);
        } else if (input.startsWith("done")) {
            // Note: "done" must be followed by an integer
            int taskIndex = Integer.parseInt(input.substring(5));
                    // TODO: handle parseInt error
            if (!isValid(taskIndex)) {
                DukeFormatter.prettyPrint(
                        "Sorry, I couldn't find the task you requested!");
                return;
            }
            Task completedTask = taskList.get(taskIndex - 1); // zero-indexed
            completedTask.markAsDone();
            DukeFormatter.prettyPrint("Nice! I've marked this task as done:\n  "
                    + completedTask.toString());
        } else if (input.startsWith("undo")) {
            // TODO: get rid of duplicated code for "done" and "undo"
            int taskIndex = Integer.parseInt(input.substring(5));
            if (!isValid(taskIndex)) {
                DukeFormatter.prettyPrint(
                        "Sorry, I couldn't find the task you requested!");
                return;
            }
            Task completedTask = taskList.get(taskIndex - 1);
            completedTask.markAsUndone();
            DukeFormatter.prettyPrint("Oh dear. I've marked this task as undone:"
                    + "\n  " + completedTask.toString());
        } else {
            addNewTask(input);
            int numberOfTasks = taskList.size();
            DukeFormatter.prettyPrint("Got it. I've added this task:"
                    + "\n  " + taskList.get(numberOfTasks - 1)
                    + "\nNow you have " + numberOfTasks + " tasks in the list.");
        }
    }

    /**
     * Add a new task to the list according to the given task type.
     * @param task      Description of task and other relevant information.
     */
    private static void addNewTask(String task) {
        // TODO: reorganise and reduce code duplication
        if (task.startsWith("todo")) { // Todo
            taskList.add(new Todo(task.substring(5)));
        } else if (task.startsWith("event")) { // Event
            String[] taskDetails = task.substring(6).split(" /at ");
            taskList.add(new Event(taskDetails[0], taskDetails[1]));
        } else if (task.startsWith("deadline")) { // Deadline
            String[] taskDetails = task.substring(9).split(" /by ");
            taskList.add(new Deadline(taskDetails[0], taskDetails[1]));
        } else {
            taskList.add(new Todo(task));
        }
    }

    private static void validate(String input) throws DukeException {
    }

    /**
     * Returns true if a task corresponding to the given index exists and
     * false otherwise.
     * @param taskIndex     Task index to be validated
     * @return              True if the index is valid and false otherwise
     */
    private static boolean isValid(int taskIndex) {
        return taskIndex >= 1 && taskIndex <= taskList.size();
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
            userInput = userInput.strip();
            if (userInput.equalsIgnoreCase("bye")) { break; }
            try {
                validate(userInput);
                process(userInput);
            } catch (DukeException e) {
                DukeFormatter.prettyPrint("Oops! " + e.getMessage());
            }
        }
        DukeFormatter.prettyPrint("Bye. Hope to see you again soon!");
    }
}
