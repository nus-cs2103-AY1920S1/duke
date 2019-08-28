public class ListCommand extends Command {

    /***
     * Class constructor.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Execute list command.
     * @param storage Storage used to save tasks into local storage
     * @param tasks TaskList used to store tasks
     * @param ui UI used to interact
     */
    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) {
        ui.echoList(tasks);
    }
}
