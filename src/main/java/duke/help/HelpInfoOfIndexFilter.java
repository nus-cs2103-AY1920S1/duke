package duke.help;

public class HelpInfoOfIndexFilter extends HelpInfoOfFilterUsage {
    /**
     * Returns a string representation of help information.
     * @return a string representation of help information.
     */
    @Override
    public String getHelpInformation() {
        return "IndexFilter is used to filter tasks in your list by index of tasks.\n"
                + "Basically, you need to specify a list of indices or a range of indices.\n"
                + "1. To specify a list of indices, literally type indices(separated by spaces) of tasks.\n"
                + "2. To specify a range of indices, type [<start>:<end>]. All tasks with indices greater than"
                + "or equal to <start> and less than <end> are selected. If <start> is omitted, then it is 1 by"
                + "by default. If <end> is omitted, then it is the last index in the task list + 1.\n"
                + "It is often used with Delete or Done command.\n"
                + super.getHelpInformation();
    }
}
