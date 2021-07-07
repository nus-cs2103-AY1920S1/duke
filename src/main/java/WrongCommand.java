import java.io.IOException;

/**
 * Execute the Wrong command in Duke.
 */

public class WrongCommand extends Command {
    private String command;

    /**
     * WrongCommand constructor with string command.
     * @param command user string input
     */
    public WrongCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Execute the Wrong command.
     * @param task Tasklist data
     * @param ui Ui interfaces and strings
     * @param storage stored file
     * @return string showing incorrect command
     */

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) {
        return "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + ui.showLine();
    }

}