public class DeleteCommand extends Command {
    protected String command;
    protected int pos;
    protected Parser parser = new Parser();

    public DeleteCommand(String command) {
       this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (parser.validNumber(command, tasks.getList().size())) {
                pos = Integer.parseInt(command);
                Task currTask = tasks.deleteTask(pos);
                storage.updateTasks(currTask, "delete", pos - 1);
                ui.printDeletedTask(currTask, tasks.getList().size());
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}