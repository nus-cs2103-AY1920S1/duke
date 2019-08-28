public class DoneCommand extends Command {
    private int index;

    /**
     * Command for TaskList to set Task at index as done.
     *
     * @param indexString Index String
     * @throws IndexFormatDukeException On unable to parse string
     */
    public DoneCommand(String indexString) throws IndexFormatDukeException {
        try {
            index = Integer.parseInt(indexString.trim());
        } catch (NumberFormatException e) {
            throw new IndexFormatDukeException();
        }
    }

    /**
     * Executes the command with given TaskList, Ui and Storage.
     *
     * @param tasks   TaskList
     * @param ui      Ui
     * @param storage Storage
     * @throws DukeException On index out of bound or problems with Storage writing
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.setDone(index);
        storage.rewrite(tasks.getSerialized());
        ui.show("Nice! I've marked this task as done:\n" + t);
    }
}
