public class ListCommand extends Command {

    private String userInput;

    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.handleList(userInput, tasks);
    }
}
