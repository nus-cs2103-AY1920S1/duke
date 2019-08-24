public class DeleteCommand extends Command {
    protected int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandDukeException {
        if (taskNumber >= 0 && taskNumber < tasks.taskListSize()) {
            Task removedTask = tasks.getTask(taskNumber);
            tasks.deleteTask(taskNumber);
            ui.showDeleteTaskMessage(removedTask, tasks.taskListSize());
        } else {
            throw new InvalidCommandDukeException("☹ OOF!!! There is no task labelled that number!!");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
