public class ExitCommand extends Command {

    public ExitCommand(String filePath, String inputSplit[]) {
        super(filePath, inputSplit);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
