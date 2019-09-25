package command;

import main.Storage;
import main.TaskList;
import main.UI;
import task.Deadline;
import task.Task;


/**
 * A Command to create an instance of a Deadline Task.
 *
 * This will add the Deadline Task to the TaskList and the Storage file.
 */
public class DeadlineCommand implements Command {
    private String deadlineName;
    private String date;

    public DeadlineCommand(String deadlineName, String date) {
        this.deadlineName = deadlineName;
        this.date = date;
    }

    /**
     * Executes the Deadline command which creates a Deadline Task.
     *
     * Taking the TaskList and Storage object of the main Duke class as arguments, this command will create a new
     * Deadline Task which will then be added to the TaskList and Storage objects. The UI will also be used to print
     * a newTask message into the console.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */
    public void execute(TaskList taskList, Storage storage) {
        Task deadline = new Deadline(deadlineName, date);

        taskList.addTaskList(deadline);
        UI.newTask(taskList.getTaskList());

        storage.writeToFile(taskList.getTaskList());
    }
}
