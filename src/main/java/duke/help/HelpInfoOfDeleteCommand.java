package duke.help;

public class HelpInfoOfDeleteCommand extends HelpInformation {
    /**
     * Returns a string representation of help information.
     * @return a string representation of help information.
     */
    @Override
    public String getHelpInformation() {
        return "Delete command deletes all the tasks you specify.\n"
                + "Type 'delete <Filter>' to delete all tasks filtered out by the filter."
                + "To learn more about filter usage, type 'help filter' or other specific filters.";
    }
}
