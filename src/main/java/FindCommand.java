public class FindCommand extends Command {
    private String str;

    public FindCommand(String str) {
        this.str = str.trim();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.show(tasks.findAllIncludesAsUiString(str));
    }
}
