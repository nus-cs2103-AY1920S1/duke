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
     * Throws an exception if the given input does not have a valid format.
     * Valid formats are: 1. "list"
     *                    2. "done [taskIndex]"
     *                    3. "undo [taskIndex]"
     *                    4. "todo [description]"
     *                    5. "deadline [description] /by [time]"
     *                    6. "event [description] /at [time]"
     * @param input             Text input to be validated
     * @throws DukeException    An exception with a message describing Duke's
     *                          response to the problem
     */
    private static void validate(String input) throws DukeException {
        if (input.startsWith("done") || input.startsWith("undo")){
            if (input.length() < 6) {
                throw new DukeException(
                        "I could't find the task you requested!");
            }
        } else if (input.startsWith("todo")) {
            if (input.length() < 6) {
                throw new DukeException(
                        "I can't see the description of your todo.");
            }
        } else if (input.startsWith("event")) {
            if (input.length() < 7) {
                throw new DukeException(
                        "I need to know the description of your event.");
            } else if (!input.contains(" /at ")) {
                throw new DukeException("I also need to know when your event is.");
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() < 10) {
                throw new DukeException("I didn't catch what you need to do.");
            } else if (!input.contains(" /by ")) {
                throw new DukeException("what's the deadline for this?");
            }
        } else if (!input.equals("list")){
            throw new DukeException("I don't know what that means... :(");
        }
    }

    /**
     * If the input is "list", prints the full list of tasks. If the input is
     * "done" or "undo" followed by an integer, marks the task corresponding to
     * the index integer as done or undone respectively. Otherwise, adds the
     * given input to the list of tasks.
     * @param input     User text to be processed.
     */
    private static void process(String input) throws DukeException {
        if (input.equalsIgnoreCase("list")) {
            DukeFormatter.prettyPrint(taskList);
        } else if (input.startsWith("done") || input.startsWith("undo")) {
            try {
                int taskIndex = Integer.parseInt(input.substring(5));
                if (!isValid(taskIndex)) {
                    throw new DukeException("I couldn't find the task you requested!");
                }
                Task completedTask = taskList.get(taskIndex - 1); // zero-indexed
                if (input.startsWith("done")) {
                    completedTask.markAsDone();
                    DukeFormatter.prettyPrint(
                            "Nice! I've marked this task as done:\n  "
                                    + completedTask.toString());
                } else {
                    completedTask.markAsUndone();
                    DukeFormatter.prettyPrint(
                            "Oh dear. I've marked this task as undone:"
                                    + "\n  " + completedTask.toString());
                }
            } catch (NumberFormatException e) {
                throw new DukeException("I couldn't find the task you requested!");
            }
        } else {
            addNewTask(input);
            int numberOfTasks = taskList.size();
            DukeFormatter.prettyPrint("Got it. I've added this task:"
                    + "\n  " + taskList.get(numberOfTasks - 1)
                    + "\nNow you have " + numberOfTasks + " tasks in the list.");
        }
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
     * Add a new task to the list according to the given task type - Todo,
     * Event, or Deadline.
     * @param task      Description of task and other relevant details.
     */
    private static void addNewTask(String task) {
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

    /**
     * Takes in user input and processes it accordingly.
     * @param args  Standard arguments
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
                DukeFormatter.prettyPrint("Sorry, " + e.getMessage());
            }
        }
        DukeFormatter.prettyPrint("Bye. Hope to see you again soon!");
    }
}
