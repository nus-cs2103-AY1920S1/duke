public class EventCommand extends Command {

    private String userInput;

    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.handleEvent(userInput, tasks, storage);
    }
}
