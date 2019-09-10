package duke.command;

/**
 * Exit the GUI and closes it.
 */
public class ExitCommand extends Command {
    /**
     * End the session between Duke and the user.
     *
     * @return Duke's reply in closing the GUI and end session.
     */
    public String exit() {
        return printLine()
                + "      Bye. Hope to see you again soon!\n"
                + printLine()
                + "\n";
    }

    /**
     * Gives a string as a border.
     *
     * @return a string that forms the border for duke.
     */
    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}
