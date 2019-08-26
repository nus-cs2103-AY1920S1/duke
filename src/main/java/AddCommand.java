public class AddCommand extends Command {

    String command;

    public AddCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(command, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
