package duke.command;

import duke.exception.DukeException;
import duke.parser.DateParser;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.util.Date;

public class AddCommand extends Command {
    private Task task;
    private String type;
    private String description;
    private Date start;
    private Date end;

    /**
     * Initialises an AddCommand.
     *
     * @param type        The type of AddCommand.
     * @param nameAndDate The name and date of the task to be added.
     * @throws DukeException In the event of empty descriptions/invalid commands/ invalid command formats.
     */
    public AddCommand(String type, String[] nameAndDate) throws DukeException {
        String taskName = nameAndDate[0];
        Date[] taskDate;

        switch (type) {
        case "todo":
            task = new ToDo(taskName);
            break;
        case "deadline":
            taskDate = DateParser.parse(nameAndDate[1]);
            task = new Deadline(taskName, taskDate[0]);
            break;
        case "event":
            taskDate = DateParser.parse(nameAndDate[1]);
            task = new Event(taskName, taskDate[0], taskDate[1]);
            break;
        default:
            break;
        }
    }

    /**
     * Executes the AddCommand, adds the task to the LinkedList and saves it to the file.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param storage The Storage for saving tasks to file.
     * @return The response string.
     */
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        storage.appendTaskToFile(task);
        return ("Got it. I've added this task:\n" + task + tasks.getStatus());
    }

    /**
     * Checks if it is a bye command.
     *
     * @return A boolean: true if it is a bye command.
     */
    public boolean isExit() {
        return false;
    }
}