package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import duke.util.DukeException;
import duke.util.History;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A Command to add a task to the task list.
 */
public class CommandAdd extends Command {
    private String taskType;
    private String taskDescription;

    /**
     * Instantiates a new 'Add' Command with a given task type and description.
     * @param taskType The type of Task to add.
     * @param taskDescription The description of the Task to add.
     */
    public CommandAdd(String taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    /**
     * Creates a new Task, adds it to the TaskList, saves it to disk, and returns a confirmation.
     * @param tasks The TaskList containing the user's added Tasks.
     * @param ui The UI to interact with the user by printing instructions/messages.
     * @param storage Storage to use for loading/saving tasks from/to a file on the hard disk.
     * @param history History of commands for the current session.
     * @return Duke's response to the Command as a String.
     * @throws DukeException If task creation, adding, or saving fails.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, History history) throws DukeException {
        Task newTask = null;

        // create new task of specified type
        switch (taskType) {
        case "todo":
            newTask = new ToDo(taskDescription);
            break;
        case "deadline":
            String[] descriptionAndDeadline = taskDescription.split(" /by ", 2);
            if (descriptionAndDeadline.length != 2) {
                throw new DukeException("Deadline format incorrect, should be "
                        + "e.g. deadline description /by time");
            }
            newTask = new Deadline(
                    descriptionAndDeadline[0],
                    Parser.parseDateTimeString(descriptionAndDeadline[1])
            );
            break;
        case "event":
            String[] descriptionAndTime = taskDescription.split(" /at ", 2);
            if (descriptionAndTime.length != 2) {
                throw new DukeException("Event format incorrect, should be "
                        + "e.g. event description /at time");
            }
            newTask = new Event(
                    descriptionAndTime[0],
                    Parser.parseDateTimeString(descriptionAndTime[1])
            );
            break;
        default:
            throw new DukeException("Unknown task type.");
        }

        tasks.add(newTask);
        storage.save(tasks);
        history.add(String.format("%s %s", taskType, taskDescription), tasks);

        return ui.getTaskAddedMessage(tasks, newTask);
    }
}
