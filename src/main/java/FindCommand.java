public class FindCommand extends Command {
    String keywords = "";
    public FindCommand(String str) {
        this.keywords = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showMatch(tasks.findTasks(this.keywords));
    }

}
