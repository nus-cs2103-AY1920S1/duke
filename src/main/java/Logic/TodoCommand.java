package Logic;

import Model.Tasklist;
import Model.todo;
import Storage.Storage;
import UI.UI;

public class TodoCommand implements Command {
    private String arguments;

    public TodoCommand(String arguments){
        this.arguments = arguments;
    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        if(arguments == null){
            ui.printData("OOPS! The description of a todo cannot be empty.\n");
        } else {
            tasks.add(new todo(arguments));

            String content = "";

            content = content.concat("Got it. I've added this task:\n");
            content = content.concat("[T][✗] " + arguments +'\n');
            content = content.concat("Now you have " + tasks.size() + " tasks in this list\n");

            ui.printData(content);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
