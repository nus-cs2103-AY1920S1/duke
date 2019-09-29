package duke.command;

/**
 * Gives the list of commands to the user.
 */
public class HelpCommand extends duke.command.Command {
    /**
     * Provide instructions on the command for using Duke.
     *
     * @return a list of commands available for Duke.
     */
    public String helpCommand() {
        return printLine() + "     Here are the list of command for Duke:\n"
                + "     list: Shows the current list of task that the user has\n"
                + "     todo [description]: Todo task accompanied "
                + "with description of task\n"
                + "     deadline [description] /by [time]: Deadline "
                + "task with description and time\n"
                + "     event [description] /at [time]: Event task "
                + "with description and time\n"
                + "     find [keyword]: Given a keyword Duke will find events that match\n"
                + "     done [index]: Use index to mark that task as done\n"
                + "     delete [index]: Use index to delete that task\n"
                + "     bye: Closes Duke\n\n"
                + "     To access the help page, please type morehelp\n"
                + printLine() + "\n";
    }

    public String moreHelpCommand() {
        return printLine() + "     Webpage opened for more help on Duke\n"
                + printLine() + "\n";
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
