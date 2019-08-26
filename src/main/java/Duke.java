import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> tasks = new ArrayList<>();

    // Messages
    private static final String MESSAGE_GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_BYE      = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_ADD      = "Got it. I've added this task:\n  %s\n"
            + "Now you have %d %s in the list.";
    private static final String MESSAGE_LIST     = "Here are the tasks in your list:\n";
    private static final String MESSAGE_NO_TASKS = "You have no tasks in your list yet!";
    private static final String MESSAGE_DONE     = "Nice! I've marked this task as done:\n  %s";
    private static final String MESSAGE_DELETE   = "Noted. I've removed this task:\n  %s\n"
            + "Now you have %d %s in the list.";

    // Errors
    private static final String ERROR_INVALID_INPUT = "I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_MISSING_DESCRIPTION = "The description cannot be empty.";
    private static final String ERROR_MISSING_DESCRIPTION_AND_TIME = "The description and time cannot be empty.";
    private static final String ERROR_MISSING_TASK_ID = "The id of the task must be provided.";
    private static final String ERROR_INVALID_TASK_ID = "The id of the task must be a number. e.g. done 1";
    private static final String ERROR_MISSING_DEADLINE = "The deadline must be present. e.g. task /by Monday";
    private static final String ERROR_MISSING_EVENT_TIME = "The event time must be present. e.g. meeting /at Monday";
    private static final String ERROR_TOO_MANY_ARGUMENTS = "There are too many arguments for this command.";

    /**
     * Setups Duke.
     * @param args Setup arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs Duke.
     * Handles command management.
     */
    private void run() {
        sayGreeting();
        String input;
        Scanner sc = new Scanner(System.in);

        do {
            input = sc.nextLine();
            String[] line = input.split(" ", 2);
            try {
                switch (line[0]) {
                case "todo":
                case "deadline":
                case "event":
                    if (line.length == 1) {
                        throw new DukeException(ERROR_MISSING_DESCRIPTION);
                    }
                    addTask(line[0], line[1]);
                    break;
                case "list":
                    if (line.length != 1) {
                        throw new DukeException(ERROR_TOO_MANY_ARGUMENTS);
                    }
                    printTasks();
                    break;
                case "done": {
                    doTask(getId(line));
                    break;
                }
                case "delete": {
                    deleteTask(getId(line));
                    break;
                }
                case "bye":
                    if (line.length != 1) {
                        throw new DukeException(ERROR_TOO_MANY_ARGUMENTS);
                    }
                    break;
                default:
                    throw new DukeException(ERROR_INVALID_INPUT);
                }
            } catch (DukeException ex) {
                printFormatted(ex.getMessage());
            }
        } while (!input.equals("bye"));

        sayBye();
    }

    /**
     * Prints the greeting message for starting Duke.
     */
    private void sayGreeting() {
        printFormatted(MESSAGE_GREETING);
    }

    /**
     * Prints the bye message for exiting Duke.
     */
    private void sayBye() {
        printFormatted(MESSAGE_BYE);
    }

    /**
     * Prints output in a standardised format.
     * @param output String to be printed by Duke.
     */
    private void printFormatted(String output) {
        String horLine = "\t____________________________________________________________";
        String[] lines = output.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s\n", horLine));
        for (String line : lines) {
            stringBuilder.append(String.format("\t %s\n", line));
        }
        stringBuilder.append(String.format("%s\n", horLine));
        System.out.println(stringBuilder);
    }

    /**
     * Adds task to task list.
     * @param command Type of task.
     * @param description Description of task.
     * @throws DukeException If invalid description.
     */
    private void addTask(String command, String description) throws DukeException {
        Task task;
        switch (command) {
        case "event": {
            if (!description.matches(".+\\s/at\\s.+$")) {
                if (description.length() == 0 || description.matches("^\\s?/at\\s?$")) {
                    throw new DukeException(ERROR_MISSING_DESCRIPTION_AND_TIME);
                }
                if (description.matches("^\\s?/at.*")) {
                    throw new DukeException(ERROR_MISSING_DESCRIPTION);
                }
                if (!description.contains("/at") || description.matches(".*/at\\s?")) {
                    throw new DukeException(ERROR_MISSING_EVENT_TIME);
                }
            }
            String[] desc = description.split(" /at ");
            task = new Event(desc[0], desc[1]);
            break;
        }
        case "deadline": {
            if (!description.matches(".+\\s/by\\s.+$")) {
                if (description.length() == 0 || description.matches("^\\s?/by\\s?$")) {
                    throw new DukeException(ERROR_MISSING_DESCRIPTION_AND_TIME);
                }
                if (description.matches("^\\s?/by.*")) {
                    throw new DukeException(ERROR_MISSING_DESCRIPTION);
                }
                if (!description.contains("/by") || description.matches(".*/by\\s?")) {
                    throw new DukeException(ERROR_MISSING_DEADLINE);
                }
            }
            String[] desc = description.split(" /by ");
            task = new Deadline(desc[0], desc[1]);
            break;
        }
        default:
            task = new Todo(description);
        }
        this.tasks.add(task);
        printFormatted(String.format(MESSAGE_ADD,  task.toString(), this.tasks.size(),
                this.tasks.size() != 1 ? "tasks" : "task"));
    }

    /**
     * Prints current task listing.
     */
    private void printTasks() {
        StringBuilder lines = new StringBuilder();
        if (this.tasks.isEmpty()) {
            lines.append(MESSAGE_NO_TASKS);
            printFormatted(lines.toString());
            return;
        }
        lines.append(MESSAGE_LIST);
        for (int i = 0; i < this.tasks.size(); i++) {
            lines.append(String.format("%d. %s\n", i + 1, this.tasks.get(i).toString()));
        }
        printFormatted(lines.toString());
    }

    /**
     * Marks task as completed.
     * @param id Id of task to mark completed.
     */
    private void doTask(int id) {
        Task task = this.tasks.get(id - 1);
        task.markAsDone();
        printFormatted(String.format(MESSAGE_DONE, task.toString()));
    }

    /**
     * Deletes task from task list.
     * @param id Id of task to delete.
     */
    private void deleteTask(int id) {
        Task task = this.tasks.get(id - 1);
        this.tasks.remove(id - 1);
        printFormatted(String.format(MESSAGE_DELETE, task.toString(), this.tasks.size(),
                this.tasks.size() != 1 ? "tasks" : "task"));
    }

    /**
     * Returns parsed number from input.
     * @param input Entire line of input.
     * @return Number in rest of input.
     * @throws DukeException If rest of input is not a number.
     */
    private int getId(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException(ERROR_MISSING_TASK_ID);
        }
        int taskId;
        try {
            taskId = Integer.parseInt(input[1]);
        } catch (NumberFormatException ex) {
            throw new DukeException(ERROR_INVALID_TASK_ID);
        }
        if (taskId < 1 || taskId > this.tasks.size()) {
            throw new DukeException(ERROR_INVALID_TASK_ID);
        }
        return taskId;
    }
}
