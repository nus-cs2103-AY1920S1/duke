package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.DateTimeException;
import java.util.InputMismatchException;
/**
 * Represents a command which adds duke.task.Task to the Tasklist.
 * @see TaskList
 * @see Task
 */
public class AddCommand extends Command {
    String type;

    /**
     * Constructor for duke.command.AddCommand
     *
     * @param commandSplitBySpaces String representation of the user input
     */
    public AddCommand(String[] commandSplitBySpaces) {
        super(commandSplitBySpaces);
    }

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
     * Executes the command by using the three arguments provided
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task addTask;
        String outputString = "";
        switch (type) {
            case "todo":
                String joinedString = String.join(" ", commandSplitBySpaces);
                String details = joinedString.substring("todo".length()).trim();
                addTask = new Todo(details);
                break;
            case "deadline":
                if(commandSplitBySpaces.length == 1) {
                    throw new InputMismatchException("You are either missing description or time");
                }
                addTask = new Deadline(commandSplitBySpaces[0], commandSplitBySpaces[1]);
                break;
            case "event":
                if(commandSplitBySpaces.length == 1) {
                    throw new InputMismatchException("You are either missing description or time");
                }
                addTask = new Event(commandSplitBySpaces[0], commandSplitBySpaces[1]);
                break;
            default:
                throw new InputMismatchException("type not found");
        }
        outputString = outputString + ui.printAddedMessage();
        outputString = outputString + ui.printTask(addTask);
        taskList.add(addTask);
        outputString = outputString + ui.printNumberOfTasks(taskList);
        return outputString;
    }
}
