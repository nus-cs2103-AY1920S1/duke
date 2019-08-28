public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String command, String keyword) {
        super(command);
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showListWithKeyword(tasks, this.getKeyword());
    }

    public boolean isExit() {
        return false;
    }
}
