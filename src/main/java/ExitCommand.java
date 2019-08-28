public class ExitCommand extends Command {

    /**
     * Ui displays exit message.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Bye. Hope to see you again soon!");
    }
}
