public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) {
        ui.echoMatchingTasks(tasks.getMatchingTasks(keyword));
    }

}
