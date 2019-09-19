package duke.command;

import java.io.IOException;

import duke.exception.DukeFileSaveException;
import duke.exception.DukeMissingTagException;
import duke.exception.DukeTaskNotPresentException;
import duke.storage.DukeStorage;
import duke.task.Task;
import duke.tasklist.MyList;
import duke.ui.DukeUserInterface;

/**
 * Represents a command which contains an execute method that deletes a tag from a specified task from the task list.
 * The DeleteTagCommand object requires the task number of the task and the tag name of the tag to be deleted.
 */
public class DeleteTagCommand extends Command {
    private int taskNum;
    private String tagName;

    /**
     * Initialises a command to be tag a class with a name.
     *
     * @param taskNum The number of the task specified by the task list.
     * @param tagName The name of the tag to be deleted.
     */
    public DeleteTagCommand(int taskNum, String tagName) {
        this.taskNum = taskNum;
        this.tagName = tagName;
    }

    /**
     * Marks the specified task from the task list as done, updates the file and prints the result.
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     * @param storage The main storage of the application.
     * @throws DukeTaskNotPresentException Thrown when the index of the task is out of bounds.
     * @throws DukeFileSaveException Thrown when the file update fails.
     */
    @Override
    public String execute(MyList taskList, DukeUserInterface ui, DukeStorage storage)
            throws DukeTaskNotPresentException, DukeFileSaveException, DukeMissingTagException {
        if (taskNum < 1 || taskNum > taskList.getNumTasks()) {
            throw new DukeTaskNotPresentException();
        }
        Task task = taskList.getTask(taskNum);
        task.deleteTag(tagName);

        try {
            storage.updateList(taskList);
        } catch (IOException e) {
            throw new DukeFileSaveException();
        }
        return ui.getDeleteTagMsg(task, tagName);
    }
}
