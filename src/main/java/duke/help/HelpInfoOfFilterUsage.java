package duke.help;

public class HelpInfoOfFilterUsage extends HelpInformation {
    /**
     * Returns a string representation of help information.
     * @return a string representation of help information.
     */
    @Override
    public String getHelpInformation() {
        return "Filter is used to filter tasks in your list by certain criteria.\n"
                + "Duke supports IndexFilter, StatusFilter, TimeFilter and TypeFilter. "
                + "It is easy to use as well as makes your operations more efficiently."
                + "Type 'help <Criteria> filter' (eg. help index filter) to learn more about how to use filter.";
    }
}
