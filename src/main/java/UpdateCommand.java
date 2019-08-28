/**
 * subclass of command
 * operation to update tasks: done commands
 * */
public class UpdateCommand extends Command {
    protected String command;
    protected int pos;
    protected Parser parser = new Parser();

    public UpdateCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (parser.validNumber(command, tasks.getList().size())) {
                pos = Integer.parseInt(command);
                Task currTask = tasks.updateTask(pos);
                storage.updateTasks(currTask, "done", pos - 1);
                ui.printDoneTask(currTask);
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

    }

    public boolean isExit() {
        return false;
    }
}