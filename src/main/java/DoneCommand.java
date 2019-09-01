public class DoneCommand extends Command {

    private String userInput;

    public DoneCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.handleDone(userInput, tasks, storage);
    }
}
