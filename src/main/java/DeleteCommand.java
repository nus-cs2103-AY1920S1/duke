public class DeleteCommand extends Command {
    DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskToDelete;
        int deleteIndex;
        try {
            deleteIndex = Integer.parseInt(splitInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Argument passed to delete must be a valid integer");
        }
        try {
            taskToDelete = tasks.getIndex(deleteIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Selected task number does not exist.");
        }
        tasks.deleteTaskAt(deleteIndex);
        ui.showDeleteTask(taskToDelete, tasks.numberOfTasks());
    }
}
