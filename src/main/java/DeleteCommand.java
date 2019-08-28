public class DeleteCommand implements Command {
    int index;
    public DeleteCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.delete(index);
        ui.showDeletedMessage(task, tasks.size());
    }

    public boolean isExit() {
        return false;
    }

}
