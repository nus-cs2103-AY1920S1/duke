public class ExitCommand extends Command {
    public void execute(Ui ui, Storage storage, TaskList taskList) {}

    @Override
    public boolean shouldContinue(){
        return false;
    }
}
