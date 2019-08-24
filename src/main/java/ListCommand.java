public class ListCommand extends Command {
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.dukePrint(tasks.toString());
    }

    boolean isExit() {
        return false;
    }
}
