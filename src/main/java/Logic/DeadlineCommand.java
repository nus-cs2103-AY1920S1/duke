package Logic;

import Model.Tasklist;
import Model.deadline;
import Storage.Storage;
import UI.UI;

public class DeadlineCommand implements Command {
    private String arguments;

    /**
     * Creates an instance of DeadlineCommand with its arguments
     * @param arguments arguments of the Command
     */
    public DeadlineCommand(String arguments){
        this.arguments = arguments;
    }

    /**
     * Parses the arguments of the Command and executes it
     * @param tasks     the TaskList of Tasks
     * @param ui        The User Interface
     * @param storage   Storage
     * @return
     */
    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage) {
        String content = "";
        if(arguments == null) {
            content = "OOPS! The description of a deadline cannot be empty.\n";
        } else {
            String[] sp = arguments.split(" /by ", 2);

            tasks.add(new deadline(sp[0], sp[1]));

            content = content.concat("Got it. I've added this task:\n");
            content = content.concat("[D][x] " + sp[0] + " (by: " + sp[1] + ")\n");
            content = content.concat("Now you have " + tasks.size() + " tasks in this list\n");
        }
        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
