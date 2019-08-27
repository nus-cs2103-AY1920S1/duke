public class DeadlineCommand extends Command {

    DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int byIndex = fullCommand.indexOf(" /by ");
        if (byIndex < 0) {
            throw new DukeException("Command deadline requires an argument /by, followed by deadline date");
        }
        String deadlineDescription =  fullCommand.substring(9, byIndex);
        String by = fullCommand.substring(byIndex + 5);
        Deadline deadline = new Deadline(deadlineDescription, by);
        tasks.addTask(deadline);
        ui.showAddTask(deadline, tasks.numberOfTasks());
    }
}
