public class ByeCommand extends Command {
    public void execute(TaskList taskList, Ui ui) {
        ui.print("Bye! Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
