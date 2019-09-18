package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.InputMismatchException;

/**
 * Represents a command which adds duke.task.Task to the task list.
 *
 * @see TaskList
 * @see Task
 */
public class AddCommand extends Command {
    private final String type;


    /**
     * Constructor for duke.command.AddCommand
     *
     * @param commandSplitBySpaces String representation of the user input
     */
    public AddCommand(String type, String[] commandSplitBySpaces) {
        super(commandSplitBySpaces);
        this.type = type;
    }

    /**
     * Executes the command by using the three arguments provided.
     *
     * @param taskList taskList used to store tasks
     * @param ui User Interface
     * @param storage Storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task addTask;
        switch (type) {
        case "todo":
            addTask = createTodo();
            break;
        case "deadline":
            addTask = createDeadline();
            break;
        case "event":
            addTask = createEvent();
            break;
        default:
            throw new InputMismatchException("type not found");
        }
        assert addTask != null;
        taskList.add(addTask);
        return createMessage(ui, addTask, taskList);
    }

    private Todo createTodo() {
        String joinedString = String.join(" ", commandSplitBySpaces);
        String details = joinedString.substring("todo".length()).trim();
        return new Todo(details);
    }

    private Deadline createDeadline() {
        if (commandSplitBySpaces.length == 1) {
            throw new InputMismatchException("You are either missing description or time");
        }
        return new Deadline(commandSplitBySpaces[0], commandSplitBySpaces[1]);
    }

    private Event createEvent() {
        if (commandSplitBySpaces.length == 1) {
            throw new InputMismatchException("You are either missing description or time");
        }
        return new Event(commandSplitBySpaces[0], commandSplitBySpaces[1]);
    }

    private String createMessage(Ui ui, Task addTask, TaskList taskList) {
        String outputString = "";
        outputString = outputString + ui.printAddedMessage();
        outputString = outputString + ui.printTask(addTask);
        outputString = outputString + ui.printNumberOfTasks(taskList);
        return outputString;
    }
}
