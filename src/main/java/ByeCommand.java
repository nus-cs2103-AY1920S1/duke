/**
 * Exceute the bye command in Duke.
 */

public class ByeCommand extends Command {

    /**
     *  Bye Constructor with command string input.
     * @param command user string input
     */
    public ByeCommand(String command) {
        super(command);
    }

    /**
     * Execute the bye command. Close the program safely.
     * @param task Tasklist data
     * @param ui Ui interfaces and strings
     * @param storage stored file
     * @return Bye command string
     */
    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        return ("      Bye :) Hope to see you again soon!\n" + ui.showLine());
    }

}