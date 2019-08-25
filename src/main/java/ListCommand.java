public class ListCommand extends Command {
    public void execute(TaskList t, Ui ui, Storage storage) {
        ui.printList(t);
    }
}
