public class AddCommand extends Command {

    private Task newTaskToBeAdded;

    public AddCommand(Task newTaskToBeAdded) {
        this.newTaskToBeAdded = newTaskToBeAdded;
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTaskToBeAdded);
        ui.printAdd(newTaskToBeAdded, tasks.getSize());
    }

}
