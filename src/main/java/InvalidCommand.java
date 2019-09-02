public class InvalidCommand extends Command {

    String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the given task, prints output and updates the taskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        // Printing Output
        ui.showTopBorder();
        System.out.println("\n\t" + errorMessage);
        ui.showBottomBorder();

    }
}