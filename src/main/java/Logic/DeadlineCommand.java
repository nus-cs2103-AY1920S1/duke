package Logic;

import Model.Tasklist;
import Model.deadline;
import Storage.Storage;
import UI.UI;

public class DeadlineCommand implements Command {
    private String arguments;

    public DeadlineCommand(String arguments){
        this.arguments = arguments;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        if(arguments == null) {
            ui.printData("OOPS! The description of a deadline cannot be empty.\n");
        } else {
            String[] sp = arguments.split(" /by ", 2);

            tasks.add(new deadline(sp[0], sp[1]));

            String content = "";

            content = content.concat("Got it. I've added this task:\n");
            content = content.concat("[D][✗] " + sp[0] + " (by: " + sp[1] + ")\n");
            content = content.concat("Now you have " + tasks.size() + " tasks in this list\n");

            ui.printData(content);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
