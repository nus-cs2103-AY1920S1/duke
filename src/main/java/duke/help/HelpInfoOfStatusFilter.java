package duke.help;

public class HelpInfoOfStatusFilter extends HelpInfoOfFilterUsage {
    @Override
    public String getHelpInformation() {
        return "StatusFilter is used to filter tasks in your list by done status.\n"
                + "Basically, you need to specify the status of tasks that you want to select.\n"
                + "To select all the tasks that are done, type '//done true'.\n"
                + "To select all the tasks that are undon, type '//done false'.\n"
                + super.getHelpInformation();
    }
}
