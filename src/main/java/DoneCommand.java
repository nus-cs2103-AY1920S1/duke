public class DoneCommand extends Command {
    int i;

    public DoneCommand(int i) {
        this.i = i;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        String taskMessage = tasks.done(i);
        ui.showDoneMessage(taskMessage);
    }

    public boolean isExit() {
        return false;
    }

}