import java.util.List;

class ExitCommand implements Command {
    public void execute(List<Task> tasks, Ui ui, Storage storage) {
        ui.printBlock("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
