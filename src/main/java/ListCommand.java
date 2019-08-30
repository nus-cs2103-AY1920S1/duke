public class ListCommand extends Command {
    public void execute() {
        if (taskList.getSize() == 0) {
            ui.displaySingleLine("Nice! You have no pending tasks!");
        } else {
            ui.displaySingleLine("Here are the tasks in your list:");
            ui.displayMessage(taskList.toString(), 2);
        }
    }
}
