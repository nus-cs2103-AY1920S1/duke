public class DisplayTasksCommand implements Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        taskList.displayList();
    }
}
