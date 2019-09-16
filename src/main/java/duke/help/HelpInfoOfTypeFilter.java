package duke.help;

public class HelpInfoOfTypeFilter extends HelpInfoOfFilterUsage {
    @Override
    public String getHelpInformation() {
        return "TypeFilter is used to filter tasks in your list by task type.\n"
                + "Basically, you need to specify the type of tasks that you want to select.\n"
                + "To select all event task, type '//type event'.\n"
                + "To select all deadline tasks, type '//type deadline'.\n"
                + "To select all todo tasks, type '//type todo'.\n"
                + super.getHelpInformation();
    }
}
