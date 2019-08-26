public class ListCommand extends Command {

    /**
     * Ui lists all tasks in TaskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show(tasks.toUiString());
    }
}
