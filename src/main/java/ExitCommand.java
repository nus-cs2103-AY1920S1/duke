public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeException {
        ui.exitProgram();
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
