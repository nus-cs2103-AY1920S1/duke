public class DoneCommand extends Command {
    DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskToMarkAsDone;
        int selectedIndex;
        try {
            selectedIndex = Integer.parseInt(splitInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Argument passed to done must be a valid integer");
        }
        try {
            taskToMarkAsDone = tasks.getIndex(selectedIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Selected task number does not exist.");
        }
        taskToMarkAsDone.markAsDone();
        ui.showMarkAsDone(taskToMarkAsDone);
    }
}
