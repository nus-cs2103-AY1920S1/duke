public class DoneCommand extends Command {
    public DoneCommand(int n) {
        super.n = n;
    }
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        try {
            Task doneTask = t.list.get(n).markAsDone();
            ui.showDoneTask(doneTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please input a number that is within the list");
        }
    }
}