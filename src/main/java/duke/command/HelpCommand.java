package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class HelpCommand extends Command {

    /**
     * This is the string to be printed at the start of the program.
     */
    private String help;

    /**
     * Constructor that takes in empty String.
     * @param input Empty string.
     */
    public HelpCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        this.help = "Available commands: \n" +
                "\n" +
                "To add a new Todo Task:\n" +
                "todo <description>\n" +
                "\n" +
                "To add a new Event Task:\n" +
                "event <description> /at <day/month/year time>\n" +
                "\n" +
                "To add a new Deadline Task:\n" +
                "deadline <description> /by <day/month/year time>\n" +
                "\n" +
                "To delete a task:\n" +
                "delete <index>\n" +
                "\n" +
                "To mass delete a bunch of tasks:\n" +
                "massdelete <indexes separated by space>\n" +
                "\n" +
                "To clear the list:\n" +
                "clearall\n" +
                "\n" +
                "To mark a task as done:\n" +
                "done <index>\n" +
                "\n" +
                "To obtain the list of tasks:\n" +
                "list\n" +
                "\n" +
                "To obtain a list of task based on keyword:\n" +
                "find <keyword>\n" +
                "\n" +
                "To exit the program:\n" +
                "bye\n" +
                "\n" +
                "Glad to help!";
    }
    public String toString() {
        return this.help;
    }
}
