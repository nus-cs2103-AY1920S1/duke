package command;

import main.Archive;
import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.DateTime;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * An AddCommand object to deal with adding Tasks.
 */
public class AddCommand extends Command {

    private String taskType;
    private String taskName;
    private DateTime dateTime;

    /**
     * Constructs an AddCommand to deal with adding Tasks.
     *
     * @param taskType      The type of the task - Todo, Event, Deadline
     * @param taskDetails   Additional information of the task - task name, task date & time
     * @throws DukeException If input is in invalid format
     */
    public AddCommand(String taskType, String taskDetails) throws DukeException {
        this.taskType = taskType;
        String[] arr = taskDetails.split("/",2);
        this.taskName = arr[0].trim();
        if (taskName.equals("")) {
            throw new DukeException("The description of a " + taskType + " cannot be empty");
        }
        if (!taskType.equals("todo")) {
            try {
                this.dateTime = new DateTime(arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("No date and time found!");
            }
        }
    }

    /**
     * Executes the command to add a new task into task list.
     *
     * @param tasks     The existing task list
     * @param ui        The Ui object which interacts with the current user
     * @param storage   The Storage object which reads and writes to a specified file
     * @param archive   The Archive object for archiving purposes
     * @return          The message to be displayed upon successful execution
     * @throws DukeException    If format of task to be added is invalid
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, Archive archive) throws DukeException {
        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = new ToDo(taskName);
            break;
        case "deadline":
            newTask = new Deadline(taskName, dateTime);
            break;
        case "event":
            newTask = new Event(taskName, dateTime);
            break;
        default:
            throw new DukeException("Invalid task format"); // should never reach here :)
        }
        tasks.addTask(newTask);
        String res = ui.dukeEchoString("Got it. I've added this task:",
                newTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
        return res;
    }

    /**
     * Overrides toString method.
     *
     * @return String format of AddCommand for debugging.
     */
    @Override
    public String toString() {
        return "Add " + this.taskType + ": " + this.taskName + " " + this.dateTime.toString();
    }

}
