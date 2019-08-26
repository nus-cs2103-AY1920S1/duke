package seedu.duke;

public class ListCommand extends Command {

    public ListCommand() {
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.printList(list);
    }
}
