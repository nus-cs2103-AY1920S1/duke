public class PrintCommand extends Command {
    public PrintCommand() {
        super();
    }

    public void execute (TaskList task, Ui ui, Storage storage) {
        ui.showText(task.printTasks());
    }
}
