public class ExitCommand extends Command {
    String message;

    public ExitCommand() {
        message = " Bye. Hope to see you again soon!\n";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.print(Ui.frontSpace + message);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
