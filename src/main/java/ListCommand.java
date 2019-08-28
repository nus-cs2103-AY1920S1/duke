public class ListCommand extends Command{

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) {
        ui.printList(tasks);
    }
}
