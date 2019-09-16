package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public class DoneCommand implements Command{
    private String arguments;

    /**
     * Creates an instance of DoneCommand with its arguments
     * @param arguments arguments of the Command
     */
    public DoneCommand(String arguments){
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
        int index = Integer.parseInt(arguments);

        tasks.get(index - 1).markAsDone();

        String content = "Nice! I've marked this task as done:\n" +
                "[" + tasks.get(index - 1).getIsDoneSymbol() + "] " + tasks.get(index - 1).getDescription() +"\n";

        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
