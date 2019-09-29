public class FindCommand extends Command{
    private String keyword;
    private TaskList foundTaskList;

    public FindCommand(String keyword) {
        this.keyword = keyword;
        foundTaskList = new TaskList();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().contains(keyword)) {
                foundTaskList.addTask(tasks.getTask(i));
            }
        }
        ui.findTaskMessage(foundTaskList);
    }
}
