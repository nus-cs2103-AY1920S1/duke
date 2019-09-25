package command;

import main.Storage;
import main.TaskList;
import main.UI;
import task.Event;
import task.Task;

import java.util.ArrayList;

/**
 * A Command to create an instance of a Event Task.
 *
 * This will add the Event Task to the TaskList and the Storage file.
 */
public class EventCommand implements Command {
    private String eventName;
    private String date;

    public EventCommand(String eventName, String date) {
        this.eventName = eventName;
        this.date = date;
    }

    /**
     * Executes the Event command which creates a Event Task.
     *
     * Taking the TaskList and Storage object of the main Duke class as arguments, this command will create a new
     * Deadline Task which will then be added to the TaskList and Storage objects. The UI will also be used to print
     * a newTask message into the console.
     *
     * @param taskList The TaskList from the main Duke object.
     * @param storage The Storage from the main Duke object.
     */
    public void execute(TaskList taskList, Storage storage) {
        ArrayList<Task> list = taskList.getTaskList();

        Task event = new Event(eventName, date);

        taskList.addTaskList(event);
        UI.newTask(list);

        storage.writeToFile(list);
    }
}
