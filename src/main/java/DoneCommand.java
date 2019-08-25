public class DoneCommand extends Command {
    private int index;

    public DoneCommand(String command) throws IndexFormatDukeException {
        try {
            index = Integer.parseInt(command.trim());
        } catch (NumberFormatException e) {
            throw new IndexFormatDukeException();
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.setDone(index);
        storage.rewrite(tasks.getSerialized());
        ui.show("Nice! I've marked this task as done:\n" + t);
    }
}
