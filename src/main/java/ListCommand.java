public class ListCommand extends Command {

    @Override
    public void execute(MyList taskList, UserInterface ui, Storage storage) {
        ui.printList(taskList);
    }
}
