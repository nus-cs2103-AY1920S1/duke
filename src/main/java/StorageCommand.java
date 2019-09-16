/**
 * Represents a storage command.
 * Commands include "save".
 * As well as searching task list and marking task as done.
 */
public class StorageCommand extends Command {

    protected TaskList taskList;

    public StorageCommand(String type, String command) {
        super(type, command);
    }

    /**
     * Executes the Storage command.
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
                storage.saveTaskList(TaskList.tasks);
                output = "Your task list has been saved!";
            } catch (Exception e) {
                output += e;
            }

        } else if (this.type.equals("display")) {
            storage.displayTaskList();
            output = "The following is your saved task list\n" + taskList.toString();
        } else {
            throw new DukeException("    TaskListCommand not identified.");
        }
        return output;
    }
}
