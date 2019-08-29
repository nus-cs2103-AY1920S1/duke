public class ExitCommand extends Command {

    public ExitCommand() {
        this.command = "bye";
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
