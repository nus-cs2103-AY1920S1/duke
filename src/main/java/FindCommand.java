public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    public void execute(TaskList tasks) {
        tasks.findTasks(keyword);
    }
}
