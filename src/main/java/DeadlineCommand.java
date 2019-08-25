public class DeadlineCommand extends Command {
    private String command;
    public DeadlineCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.checkErrorForDeadlineCommand(command, tasks);
        tasks.add(Parser.createDeadline(command));
        ui.printAddedTask(tasks.get(tasks.size() - 1));
        ui.printNoOfTaskInList(tasks);
        storage.appendFile(tasks);
    }

    public boolean isExit() {
        return false;
    }
}