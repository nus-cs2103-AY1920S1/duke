public class UpdateCommand extends Command {
    private int idxToUpdate;
    private String command;

    /**
     * Constructs a Command to update Task to TaskList.
     *
     * @param idxToUpdate the index of the Task to be updated.
     */
    public UpdateCommand(int idxToUpdate, String command) {
        this.idxToUpdate = idxToUpdate;
        this.command = command;
    }

    @Override
    boolean isExit() {
        return false;
    }

    /**
     * Executes an UpdateCommand given TaskList, UI, Storage.
     *
     * @param tasks the TaskList.
     * @param ui the UI.
     * @param storage the file storage.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] cmdList = command.split(" ");
            String keyword = cmdList[0];
            Task newTaskToReplace = Parser.handleNewTask(keyword, command);
            tasks.replaceTask(idxToUpdate, newTaskToReplace);
            ui.printUpdatedTask(newTaskToReplace, tasks.getTaskList());

        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input. Please input a valid number between 1 and "
                    + tasks.getSize());
        }
    }

}
