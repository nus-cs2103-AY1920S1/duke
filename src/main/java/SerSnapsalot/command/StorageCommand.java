package SerSnapsalot.command;

import SerSnapsalot.ui.Ui;
import SerSnapsalot.taskList.TaskList;
import SerSnapsalot.storage.Storage;
import SerSnapsalot.exception.DukeException;
/**
 * Represents a storage command.
 * Commands include "save", "archive".
 * As well as searching task list and marking task as done.
 */
public class StorageCommand extends Command {

    protected TaskList taskList;

    public StorageCommand(String type, String command) {
        super(type, command);
    }

    /**
     * Executes the storage.Storage command.
     * Saving task list to file: "save".
     * Load file to new task list and display: "display".
     *
     * @param ui       The Ui currently running.
     * @param taskList The TaskList Class containing the task list.
     * @param storage  The Storage class containing the name of file the be read.
     * @throws DukeException If command is not identified.
     * @return output The String output for GUI message.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        String output = "";
        if (this.type.equals("save")) {
            try {
                output = storage.saveTaskList(TaskList.tasks);
            } catch (Exception e) {
                output += e;
            }

        } else if (this.type.equals("display")) {
            storage.displayTaskList();

            output = "Here's the task list your suit saved\n" + taskList.toString();
        } else if (this.type.equals("archive")) {
            try {
                assert (this.command.equals("save") || this.command.equals("load")) : command;
                if (this.command.contains("save")) {
                    output = storage.archiveSaveTaskList(TaskList.tasks);
                    taskList.clearTaskList();
                } else if (this.command.contains("load")) {
                    taskList = storage.archiveLoadTaskList();
                    output = "The archived task list has been loaded";
                } else {
                    throw new DukeException("Storage Exception: TaskListCommand not identified.");
                }
            } catch (AssertionError e) {
                String assertionError = "Assertion Error";
                output = assertionError;
            }
        }
        return output;
    }
}
