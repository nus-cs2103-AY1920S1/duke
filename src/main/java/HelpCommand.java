public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder help = new StringBuilder();
        help.append("Welcome to Help Page\n");
        help.append("----------------------\n");
        help.append("*Commands, Descriptions and Examples Below:*\n");
        help.append("list : Displays all items in list.\n" + "Example - list\n");
        help.append("todo : A task with no specific date and time.\n" +
                "Example - todo homework\n");
        help.append("deadline : A task that id due by specific date and time.\n" +
                "Example - deadline return book /by 2/12/2019 1800\n");
        help.append("event : A task occurring at a specific date and time\n" +
                "Example - event project meeting /at 12/12/2019 1800\n");
        help.append("done : Marks task at specified index in the list as completed.\n" +
                "Example - done 1\n");
        help.append("find : Finds a task that contains the keyword given.\n" +
                "Example - find book\n");
        help.append("delete : Removes task at specified index from the list.\n" +
                "Example - delete 3\n");
        help.append("bye : Terminates the program.\n" + "Example - bye\n");

        return help.toString();
    }
}
