public class DoneTaskCommand extends Command {

    String itemIndex;

    public DoneTaskCommand(String itemIndex) {
        this.itemIndex = itemIndex;
    }

    public boolean isTerminated() {
        return false;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        // convert string to int
        int index = Integer.parseInt(itemIndex) - 1;
        try {
            if (index < 0 || index >= tasklist.size()) {
                throw new InvalidIndexException();
            } else {
                Task item = tasklist.get(index);
                // tick completed task
                item.setDone();
                ui.sendMessage("Nice! I've marked this task as done: ");
                ui.sendMessage("  " + item.toString());
            }
        } catch (DukeException error) {
            ui.sendMessage(error.toString());
        }
    }
}
