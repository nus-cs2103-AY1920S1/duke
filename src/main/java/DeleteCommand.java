public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(index);
            tasks.removeTask(index);
            storage.writeToFile(tasks);
            return ui.removeTaskMessage(tasks, task);
        } catch (Exception e) {
            return "OOPS!!! That task in not in the list!";
        }
    }
}
