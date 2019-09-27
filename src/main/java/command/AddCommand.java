package command;

import exception.DukeException;
import filewriter.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;


/**
 * Command to add a new Task to Task list.
 */
public class AddCommand extends Command {
    String specifics;

    /**
     * Constructor for AddCommand.
     * @param taskType type of task (e.g. deadline, event etc.)
     * @param specifics includes the task name as well as the date and time in the case of DEADLINE and EVENT.
     */
    public AddCommand(FullCommand taskType, String specifics) {
        super.type = taskType;
        this.specifics = specifics;
    }

    /**
     * Used to check if command is an ExitCommand.
     * @return false as command is an AddCommand.
     */
    public boolean isExit() {
        assert(!super.type.getName().equals("bye"));
        return false;
    }

    /**
     * adds task corresponding to command type and specifics to TaskList.
     * @param tasks TaskList to add the task to.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage updates data record of TaskList stored in storage.filepath.
     * @throws DukeException Creating new Deadline and Event throws exception if not written in correct format.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask;
        switch (type) {
        case TODO:
            newTask = new Todo(specifics);
            break;
        case DEADLINE:
            newTask = new Deadline(specifics);
            break;
        default:
            newTask = new Event(specifics);
            break;
        }
        tasks.addTask(newTask);
        ui.readTask(newTask, tasks.taskNum);
    }
}
