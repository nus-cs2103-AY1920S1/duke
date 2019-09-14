public class AddCommand extends Command {

    private Task newTaskToBeAdded;

    /**
     * Constructs a Command to add Task to TaskList.
     *
     * @param newTaskToBeAdded is the task that will be added into TaskList
     */
    public AddCommand(Task newTaskToBeAdded) {
        this.newTaskToBeAdded = newTaskToBeAdded;
    }

    @Override
    boolean isExit() {
        return false;
    }

    /**
     * Executes an AddCommand given TaskList, UI, Storage.
     *
     * @param tasks the TaskList.
     * @param ui the UI.
     * @param storage the file storage.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTaskToBeAdded);
        ui.printAdd(newTaskToBeAdded, tasks.getSize());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof AddCommand)) {
            return false;
        }
        AddCommand ac = (AddCommand) o;

        return ac.newTaskToBeAdded.toString().equalsIgnoreCase(this.newTaskToBeAdded.toString());
    }

}
