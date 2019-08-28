public class ByeCommand extends Command{
    public ByeCommand(String stringCommand){
        super(stringCommand);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.bye();
        storage.rewriteData();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
