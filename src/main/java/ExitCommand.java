/**
 * Exit command that inherits from Command to exit from the program.
 */
public class ExitCommand extends Command {
    /**
     * Constructs a new ExitCommand object.
     *
     * @param input input from user.
     */

    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Executes the command to exit from the program.
     *
     * @param tasks   current list of tasks.
     * @param ui      Ui object.
     * @param storage Storage object to save and load files.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Duke.print("Bye. Hope to see you again soon!");
        this.isExit = true;
    }


}
