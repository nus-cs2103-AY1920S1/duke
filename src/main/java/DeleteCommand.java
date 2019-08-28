public class DeleteCommand extends Command {
    private int index;

    /**
     * Command for TaskList to remove Task at index.
     *
     * @param indexString Index String
     * @throws IndexFormatDukeException On unable to parse string
     */
    public DeleteCommand(String indexString) throws IndexFormatDukeException {
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
        Task t = tasks.remove(index);
        storage.rewrite(tasks.getSerialized());
        ui.show("Noted. I've removed this task:\n  "
            + t
            + "Now you have " + tasks.size() + " tasks in the list.");
    }
}
