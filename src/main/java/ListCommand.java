public class ListCommand extends Command {

    public ListCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        if (listOfTasks.size() == 0) {
            throw new DukeException("     The list is empty!");
        }
        ui.printList();
    }
}
