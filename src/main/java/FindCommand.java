public class FindCommand extends Command {
    protected String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showFindTasksMessage(tasks.findTasks(this.keyWord));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
