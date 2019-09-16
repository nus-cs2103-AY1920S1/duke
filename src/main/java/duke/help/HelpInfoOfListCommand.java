package duke.help;

public class HelpInfoOfListCommand extends HelpInformation {
    @Override
    public String getHelpInformation() {
        return "List command list all the tasks you specify.\n"
                + "Type 'list <Filter>' to list all tasks filtered out by the filter."
                + "All filter except index filter is used."
                + "If filter is omitted, then all the tasks in your list is shown.\n"
                + "To learn more about filter usage, type 'help filter' or other specific filters.";
    }
}
