package duke.help;

public class HelpInfoOfDoneCommand extends HelpInformation {
    /**
     * Returns a string representation of help information.
     * @return a string representation of help information.
     */
    @Override
    public String getHelpInformation() {
        return "Done command set status of all the tasks you specify to be done.\n"
                + "Type 'done <filter>' to set all tasks filtered out by the filter as done."
                + "To learn more about filter usage, type 'help filter' or other specific filters.";
    }
}
