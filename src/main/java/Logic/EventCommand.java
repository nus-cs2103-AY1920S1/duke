package Logic;

import Model.Tasklist;
import Model.event;
import Storage.Storage;
import UI.UI;

public class EventCommand implements Command {
    private String arguments;

    /**
     * Creates an instance of EventCommand with its arguments
     * @param arguments arguments of the Command
     */
    public EventCommand(String arguments){
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
        if(arguments == null){
            content = "OOPS!!! The description of a event cannot be empty.";
        } else {
            String[] sp = arguments.split(" /at ", 2);

            tasks.add(new event(sp[0], sp[1]));

            content = content.concat("Got it. I've added this task:\n");
            content = content.concat("[E][x] " + sp[0] + " (at: " + sp[1] + ")\n");
            content = content.concat("Now you have " + tasks.size() + " tasks in this list\n");
        }
        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
