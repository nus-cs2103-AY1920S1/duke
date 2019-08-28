public class DoneCommand implements Command {
    int index;
    public DoneCommand(String index) {
        this.index = Integer.parseInt(index);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.done(index);
        ui.showMarkedAsDone(task);
    }

    public boolean isExit() {
        return false;
    }

}
