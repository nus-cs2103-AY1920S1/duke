public class DeleteCommand extends Command {

    String command;

    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(command, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
