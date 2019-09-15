package Logic;

import Model.Tasklist;
import Model.event;
import Storage.Storage;
import UI.UI;

public class EventCommand implements Command {
    private String arguments;

    public EventCommand(String arguments){
        this.arguments = arguments;
    }

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
