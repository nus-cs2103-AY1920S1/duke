public class DoneCommand extends Command {
    protected int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandDukeException {
        if (taskNumber >= 0 && taskNumber < tasks.taskListSize()) {
            tasks.markAsDone(taskNumber);
            ui.showMarkTaskAsDoneMessage(tasks.getTask(taskNumber));
        } else {
            throw new InvalidCommandDukeException("☹ OOF!! There is no task labelled that number!!");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
