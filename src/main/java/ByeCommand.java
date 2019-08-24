public class ByeCommand extends Command {
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.dukePrint("Bye. Hope to see you again soon!");
    }

    boolean isExit() {
        return true;
    }
}
