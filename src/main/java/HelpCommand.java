public class HelpCommand extends Command {
    public HelpCommand() {

    }
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //do something
        ui.showMessage("List of available commands");
        ui.showMessage(6, "- help || shows the list of avaibale commands");
        ui.showMessage(6, "- list || Displays the tasks currently stored");
        ui.showMessage(6, "- todo <task name> || adds a todo task to the list");
        ui.showMessage(6, "- event <task name> /at <time with format dd/mm/yyyy hh:mm>");
        ui.showMessage(10, "adds an event task to the list");
        ui.showMessage(6, "- deadline <task name> /by <time with format dd/mm/yyyy hh:mm>");
        ui.showMessage(10, "adds an deadline task to the list");
        ui.showMessage(6, "- done <task number> || completes the indicated task on the list");
        ui.showMessage(6, "- delete <task number> || deletes the indeicated task from the list");
        ui.showMessage(6, "- bye || closes the app");
    }
}
