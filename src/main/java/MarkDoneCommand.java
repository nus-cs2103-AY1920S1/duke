public class MarkDoneCommand extends Command {
    private String command;
    public MarkDoneCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.checkMarkDoneError(command, tasks);
        int curr = Parser.taskToMarkDone(command);
        tasks.get(curr - 1).markAsDone();
        ui.printMarkDoneMsg(tasks.get(curr - 1));
        storage.writeFile(tasks);
    }

    public boolean isExit() {
        return false;
    }
}