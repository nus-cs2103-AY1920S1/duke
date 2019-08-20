import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> tasks = new ArrayList<>();

    private static final String MESSAGE_GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_BYE      = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_ADD      = "Got it. I've added this task:\n  %s\nNow you have %d %s in the list.";
    private static final String MESSAGE_LIST     = "Here are the tasks in your list:\n";
    private static final String MESSAGE_DONE     = "Nice! I've marked this task as done:\n  %s";

    private static final String ERROR_INVALID_INPUT = "I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_MISSING_DESCRIPTION = "The description of a %s cannot be empty.";
    private static final String ERROR_MISSING_TASK_ID = "The id of the task must be provided.";
    private static final String ERROR_INVALID_TASK_ID = "The id of the task must be a number. e.g. done 1";
    private static final String ERROR_MISSING_DEADLINE = "The deadline must be present. e.g. task /by Monday";
    private static final String ERROR_MISSING_EVENT_TIME = "The event time must be present. e.g. event /at Monday";
    private static final String ERROR_TOO_MANY_ARGUMENTS = "There are too many arguments for this command.";

    /**
     * Setup Duke.
     * @param args Setup arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Run Duke.
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
                        throw new DukeException(String.format(ERROR_MISSING_DESCRIPTION, line[0]));
                    }
                    addTask(line[0], line[1]);
                    break;
                case "list":
                    if (line.length != 1) {
                        throw new DukeException(ERROR_TOO_MANY_ARGUMENTS);
                    }
                    printTasks();
                    break;
                case "done":
                    if (line.length == 1) {
                        throw new DukeException(ERROR_MISSING_TASK_ID);
                    }
                    int taskId = -1;
                    try {
                        taskId = Integer.parseInt(line[1]);
                    } catch (NumberFormatException ex) {
                        throw new DukeException(ERROR_INVALID_TASK_ID);
                    }
                    doTask(taskId);
                    break;
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

    private void sayGreeting() {
        printFormatted(MESSAGE_GREETING);
    }

    private void sayBye() {
        printFormatted(MESSAGE_BYE);
    }

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

    private void addTask(String command, String description) throws DukeException {
        Task task;
        switch (command) {
        case "event": {
            String[] desc = description.split(" /at ");
            if (desc.length == 1) {
                throw new DukeException(ERROR_MISSING_EVENT_TIME);
            }
            task = new Event(desc[0], desc[1]);
            break;
        }
        case "deadline": {
            String[] desc = description.split(" /by ");
            if (desc.length == 1) {
                throw new DukeException(ERROR_MISSING_DEADLINE);
            }
            task = new Deadline(desc[0], desc[1]);
            break;
        }
        default:
            task = new Todo(description);
        }
        this.tasks.add(task);
        printFormatted(String.format(MESSAGE_ADD,  task.toString(), this.tasks.size(),
                                     this.tasks.size() > 1 ? "tasks" : "task"));
    }

    private void printTasks() {
        StringBuilder lines = new StringBuilder();
        int counter = 0;
        lines.append(MESSAGE_LIST);
        for (Task task : this.tasks) {
            counter++;
            lines.append(String.format("%d. %s\n", counter, task.toString()));
        }
        printFormatted(lines.toString());
    }

    private void doTask(int id) {
        Task task = this.tasks.get(id - 1);
        task.markAsDone();
        printFormatted(String.format(MESSAGE_DONE, task));
    }
}
