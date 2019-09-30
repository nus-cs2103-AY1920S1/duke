public class ErrorCommand extends Command {
    private String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return message;
    }
}
