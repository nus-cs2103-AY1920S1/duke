public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String command) throws IndexFormatDukeException {
        try {
            index = Integer.parseInt(command.trim());
        } catch (NumberFormatException e) {
            throw new IndexFormatDukeException();
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.remove(index);
        storage.rewrite(tasks.getSerialized());
        ui.show("Noted. I've removed this task:\n  " +
            t +
            "Now you have " + tasks.size() + " tasks in the list.");
    }
}
