public class ListCommand extends Command {

    @Override
    boolean isExit() {
        return false;
    }

    /**
     * Executes an ListCommand given TaskList, UI, Storage.
     *
     * @param tasks the TaskList.
     * @param ui the UI.
     * @param storage the file storage.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTaskList());
    }
}
