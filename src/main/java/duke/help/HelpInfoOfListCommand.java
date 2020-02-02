package duke.help;

public class HelpInfoOfListCommand extends HelpInformation {
    /**
     * Returns a string representation of help information.
     * @return a string representation of help information.
     */
    @Override
    public String getHelpInformation() {
        return "List command list all the tasks you specify.\n"
                + "Type 'list <filter>' to list all tasks filtered out by the filter."
                + "All filter except index filter is used."
                + "If filter is omitted, then all the tasks in your list is shown.\n"
                + "To learn more about filter usage, type 'help filter' or other specific filters.";
    }
}
