public class PrintUserInputErrorCommand implements Command {
    String message;

    public PrintUserInputErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Ui.print(message);
    }
}
