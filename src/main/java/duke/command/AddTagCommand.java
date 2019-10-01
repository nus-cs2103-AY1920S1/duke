package duke.command;

import duke.exception.DukeDuplicateTagException;
import duke.exception.DukeFileSaveException;
import duke.exception.DukeTaskNotPresentException;
import duke.storage.DukeStorage;
import duke.task.Task;
import duke.tasklist.MyList;
import duke.ui.DukeUserInterface;

import java.io.IOException;

/**
 * Represents a command which contains an execute method that adds a tag to a specified task in the task list.
 * The AddTagCommand object requires the task number of the task and the name of the task to be added to the list.
 */
public class AddTagCommand extends Command {
    private int taskNum;
    private String tagName;

    /**
     * Initialises a command to be tag a class with a name.
     *
     * @param taskNum The number of the task specified by the task list.
     * @param tagName The name of the tag to be added.
     */
    public AddTagCommand(int taskNum, String tagName) {
        this.taskNum = taskNum;
        this.tagName = tagName;
    }

    /**
     * Adds a tag to the specified task, updates the file and prints the result.
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     * @param storage The main storage of the application.
     * @throws DukeTaskNotPresentException Thrown when the index of the task is out of bounds.
     * @throws DukeFileSaveException Thrown when the file update fails.
     */
    @Override
    public String execute(MyList taskList, DukeUserInterface ui, DukeStorage storage)
            throws DukeTaskNotPresentException, DukeFileSaveException, DukeDuplicateTagException {
        if (taskNum < 1 || taskNum > taskList.getNumTasks()) {
            throw new DukeTaskNotPresentException();
        }
        Task task = taskList.getTask(taskNum);
        task.addTag(tagName);

        try {
            storage.updateList(taskList);
        } catch (IOException e) {
            throw new DukeFileSaveException();
        }
        return ui.getAddTagMsg(task, tagName);
    }
}
