public class ByeCommand extends Command {

    public ByeCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        ui.printBye();
        System.exit(0);
    }
}
