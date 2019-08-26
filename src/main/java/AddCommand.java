public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        super(task);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.add(this.task);
        UI.printSuccessfulAddMessage(taskList.get(taskList.size()-1), taskList.size());
    }
}
