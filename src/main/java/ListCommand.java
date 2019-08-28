public class ListCommand extends Command {

    @Override
    boolean isExit() {
        return false;
    }

    /**
     * execute() will print out all Tasks in TaskList.
     * @param tasks is the TaskList
     * @param ui is the Ui
     * @param storage is the Storage
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTaskList());
    }
}
