public class DeadlineCommand extends AddCommand {
    public DeadlineCommand() {

    }
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String input = ui.getLastCommand();
        String command = input.substring(9).trim();
        AddCommand.addTask(tasks, ui, storage, new DeadlinesTask(command));
    }
}