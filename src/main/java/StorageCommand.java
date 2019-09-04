/**
 * Represents a storage command
 * Commands include "save",
 * As well as searching task list and marking task as done
 */
public class StorageCommand extends Command {

    protected TaskList taskList;

    public StorageCommand (String type, String command) {
        super(type, command);
    }

    /**
     * Executes the Storage command
     * Saving task list to file: "save"
     * Load file to new task list and display: "display"
     *
     * @param ui  The Ui currently running
     * @param taskList The TaskList Class containing the task list
     * @param storage The Storage class containing the name of file the be read
     * @throws DukeException If command is not identified
     */
    @Override
    public void execute (Ui ui, TaskList taskList, Storage storage) throws DukeException {
        if (this.type.equals("save")) {
            storage.saveTaskList(taskList.tasks);
        } else if (this.type.equals("display")) {
            storage.displayTaskList();
        } else {
            throw new DukeException("    TaskListCommand not identified.");
        }
    }


}
