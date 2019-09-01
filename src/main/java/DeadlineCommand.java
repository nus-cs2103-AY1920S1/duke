public class DeadlineCommand extends Command {

    private String userInput;

    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.handleDeadline(userInput, tasks, storage);
    }
}
