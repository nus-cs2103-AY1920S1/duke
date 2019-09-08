public class ListCommand extends Command {

    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        return ui.printList(currentTaskList);
    }
}
