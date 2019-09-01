package duke;

public class DeleteCommand extends Command {
    DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task taskToDelete;
        int deleteIndex;
        try {
            deleteIndex = Integer.parseInt(splitInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Argument passed to delete must be a valid integer");
        }
        try {
            taskToDelete = tasks.getTaskAtIndex(deleteIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Selected task number does not exist.");
        }
        tasks.deleteTaskAt(deleteIndex);
        String output = "Noted. I've removed this task: \n"
                + taskToDelete.toString()
                + String.format("\nNow you have %d tasks in the list.", tasks.getNumberOfTasks());
        return output;
    }
}
