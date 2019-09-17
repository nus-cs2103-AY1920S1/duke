package command;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import taskList.TaskList;
import ui.Ui;

/**
 * Represents an adding to task list command.
 * Commands include adding Deadlines, Events and ToDos to the task list.
 */
public class AddCommand extends Command {

	public AddCommand(String type, String command) {
        super(type, command);
    }

    /**
     * Executes the command.AddCommand.
     * Adds Deadlines: "deadline".
     * Adds Events: "event".
     * Adds ToDos: "todo".
     *
     * @param ui       The ui.Ui currently running.
     * @param taskList The TaskList Class containing the task list.
     * @param storage  The Storage class containing the name of file the be read.
     * @return output The String output for GUI message.
     * @throws DukeException If unable to identify command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        Task newTask;
        switch (this.type) {
        case "deadline":

            newTask = new Deadline(command);
            try {
                newTask.understandDate();
            } catch (Exception e) {
                System.out.println(e);
            }
            TaskList.tasks.add(newTask);
            break;

        case "event":
            newTask = new Event(command);
            try {
                newTask.understandDate();
            } catch (Exception e) {
                System.out.println(e);
            }
            TaskList.tasks.add(newTask);
            break;

        case "todo":
            newTask = new ToDo(command);
            TaskList.tasks.add(newTask);
            break;

        default:
            throw new DukeException("AddCommand Exception: Unable to identify command");
        }
        String output = "Got it. I've added this task:\n" + newTask + "\n";
        output += "Now you have " + TaskList.tasks.size() + " tasks in the list";
        return output;
    }
}
