package duke.help;

public class HelpInfoOfTimeFilter extends HelpInfoOfFilterUsage {
    /**
     * Returns a string representation of help information.
     * @return a string representation of help information.
     */
    @Override
    public String getHelpInformation() {
        return "TimeFilter is used to filter tasks in your list by the time associated with the tasks.\n"
                + "Basically, you need to specify time period of tasks that you want to select.\n"
                + "To select all the tasks within a certain time, type '//<time comparator> (DD/MM/YYYY) HH:MM'.\n"
                + "If date is omitted, it is set to today by default.\n"
                + "<time operator> consists of on, before, after, from, until\n"
                + super.getHelpInformation();
    }
}
