public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

        // Delete from file, mark as done, reinsert into file
        storage.deleteTaskFromFile(tasks.allTasks.get(index));
        tasks.allTasks.get(index).markAsDone();
        ui.printMessage("Nice! I've marked this task as done:\n" + tasks.allTasks.get(index));
        storage.appendTaskToFile(tasks.allTasks.get(index));
    }

    public boolean isExit() {
        return false;
    }
}
