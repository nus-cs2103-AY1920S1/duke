public class DoneCommand extends Command {

    private String instruction;

    /**
     * Represents the command of marking a task as done.
     * @param instruction refers to the task number which needs to be marked as done
     */
    public DoneCommand(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Returns the message DukeBot will show after each interaction with the user.
     * @param tasks refers to the list of tasks DukeBot is handling
     * @param ui refers to the instance of the UI class which handles output messages
     * @param storage refers to the instance of Storage class which handles read-write to the .txt file
     * @return response of DukeBot to the given user query
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            if (instruction.equals("")) {
                throw new DukeException("☹ OOPS!!! Please specify which task is done");
            } else if (Integer.parseInt(instruction) > tasks.getListSize()) {
                throw new DukeException("☹ OOPS!!! Task " + instruction + " does not exist");
            } else {
                tasks.markAsDone(Integer.parseInt(instruction) - 1);
                try {
                    storage.update(tasks.getList());
                    return ui.showTaskIsDoneMsg(tasks.getItemAtIndex(Integer.parseInt(instruction) - 1));
                } catch (Exception exp) {
                    return ui.showErrorMsg(exp.getMessage());
                }
            }
        } catch (DukeException exp) {
            return ui.showErrorMsg(exp.getMessage());
        }
    }
}
