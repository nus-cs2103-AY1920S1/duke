package duke.command;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.TaskList;
import duke.task.Task;

import duke.ui.Ui;

import duke.storage.Storage;

public class AddCommand extends Command {

    String command;

    /**
     * Constructor to create an AddCommand object.
     *
     * @param command User's command.
     */
    public AddCommand(String command) {
        this.command = command;
    }

    /**
     * Get the TaskList object to add the task into the list.
     *
     * @param taskList TaskList object containing the current tasks list.
     * @param ui The Ui object we are currently using.
     * @param storage The Storage object we are currently using.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null || ui != null || storage != null :
                "TaskList, Ui and Storage objects cannot be null";

        String response = "";
        String[] words = command.split(" ");
        String type = words[0];
        Task task = getCommandTask(command, type);
        response = taskList.addTask(task, ui);
        return response;

    }

    private Task getCommandTask(String command, String type) throws DukeException {
        try {
            if (type.equalsIgnoreCase("todo")) {
                if (command.substring(5).trim().isEmpty()) {
                    throw new IllegalArgumentException("OOPS!!! Description cannot be empty\n");
                }
                return new Todo(command.substring(5).trim());
            } else {
                int index = command.indexOf('/');
                if (type.equalsIgnoreCase("deadline")) {
                    return new Deadline(command.substring(9, index - 1).trim(), command.substring(index + 4).trim());
                } else if (type.equalsIgnoreCase("event")) {
                    return new Event(command.substring(6, index - 1).trim(), command.substring(index + 4).trim());
                } else {
                    throw new IllegalArgumentException("OOPS!!! No such task type.\n");
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Wrong input format. \n"
                    + "\"Todo <description>\" or\n"
                    + "\"Deadline <description> /by <DD/MM/YYYY> <XX:XX>\" or\n"
                    + "\"Event <description> /at <DD/MM/YYYY> <XX:XX>\"\n");
        } catch (IllegalArgumentException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
