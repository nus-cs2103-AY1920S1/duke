public class TodoCommand extends Command {

    private String userInput;

    public TodoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.handleTodo(userInput, tasks, storage);
    }
}
