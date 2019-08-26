public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

        StringBuilder sb = new StringBuilder("Noted. I've removed this task:\n" + tasks.allTasks.get(index));
        storage.deleteTaskFromFile(tasks.allTasks.get(index));
        tasks.allTasks.remove(index);
        if (tasks.allTasks.size() == 1) {
            sb.append("\nNow you have " + tasks.allTasks.size() + " task in the list.");
        } else {
            sb.append("\nNow you have " + tasks.allTasks.size() + " tasks in the list.");
        }
        ui.printMessage(sb.toString());
    }

    public boolean isExit() {
        return false;
    }
}
