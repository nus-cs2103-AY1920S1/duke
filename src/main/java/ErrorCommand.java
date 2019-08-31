public class ErrorCommand extends Command{
    private String msg;
    public ErrorCommand(String msg) {
        this.msg = msg;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return msg;
    }
}
