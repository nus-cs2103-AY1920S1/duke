package Logic;

import Model.Tasklist;
import Model.todo;
import Storage.Storage;
import UI.UI;

public class TodoCommand implements Command {
    private String arguments;

    /**
     * Creates an instance of TodoCommand with its arguments
     * @param arguments arguments of the Command
     */
    public TodoCommand(String arguments){
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
            content = "OOPS! The description of a todo cannot be empty.\n";
        } else {
            tasks.add(new todo(arguments));

            content = content.concat("Got it. I've added this task:\n");
            content = content.concat("[T][x] " + arguments +'\n');
            content = content.concat("Now you have " + tasks.size() + " tasks in this list\n");
        }
        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
