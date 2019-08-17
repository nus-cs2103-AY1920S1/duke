//@@author Parcly-Taxel
/**
 * Class representing a command to exit Duke.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        exit = true;
    }
    
    public void execute(TaskList tl, Ui ui) {
        ui.printMessage("Bye. Hope to see you again soon!");
    }
}
