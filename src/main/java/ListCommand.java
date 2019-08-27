public class ListCommand extends Command {
    public ListCommand(String taskInformation) {
        super(taskInformation);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.printTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
