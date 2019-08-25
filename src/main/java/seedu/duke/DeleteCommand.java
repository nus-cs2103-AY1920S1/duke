package seedu.duke;

public class DeleteCommand extends Command {
    private String command;
    public DeleteCommand(String command) {
        this.command = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        //deleting task
        ui.checkErrorForDeleteCommand(command, tasks);
        int curr = Parser.taskToDelete(command);
        tasks.remove(curr - 1);
        ui.printDeletedTaskMsg(tasks.get(curr - 1));
        ui.printNoOfTaskInList(tasks);
        //write 'tasks' into data file, overwriting all contents
        storage.writeFile(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
