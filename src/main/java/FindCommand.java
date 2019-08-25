public class FindCommand extends Command {
    String keywords = "";
    public FindCommand(String str) {
        this.keywords = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showMatch(tasks.findTasks(this.keywords));
    }

}
