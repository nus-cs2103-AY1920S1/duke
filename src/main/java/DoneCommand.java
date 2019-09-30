public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(index);
            task.setDone(true);
            storage.writeToFile(tasks);
            return ui.doneTaskMessage(task);
        } catch (Exception e) {
            return "OOPS!!! That task in not in the list!";
        }
    }
}
