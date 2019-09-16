package duke.help;

public class HelpInfoOfFindCommand extends HelpInformation {
    @Override
    public String getHelpInformation() {
        return "Find command looks for all tasks that possibly match your keyword.\n"
                + "Type 'find <keyword>' to look for all tasks match the <keyword>.\n";
    }
}
