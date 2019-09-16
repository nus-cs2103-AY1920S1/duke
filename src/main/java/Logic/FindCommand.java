package Logic;

import Model.Tasklist;
import Storage.Storage;
import UserInterface.UI;

import java.util.ArrayList;

public class FindCommand implements Command{
    private String arguments;

    /**
     * Creates an instance of FindCommand with its arguments
     * @param arguments arguments of the Command
     */
    public FindCommand(String arguments){
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
            content = "OOPS!!! The description of a find cannot be empty.";
        } else {
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            int i;
            for(i = 0; i < tasks.size(); i++) {
                if(tasks.get(i).getDescription().contains(arguments)){
                    indexes.add(i);
                }
            }
            for(i = 0; i < indexes.size(); i++) {
                content = content.concat((i + 1) + ". ");
                content = content.concat("[" + tasks.get(indexes.get(i)).getSymbol() + "]");
                content = content.concat("[" + tasks.get(indexes.get(i)).getIsDoneSymbol() + "]");
                content = content.concat(" " + tasks.get(indexes.get(i)).getDescription());
                if(tasks.get(indexes.get(i)).getSymbol() == 'D'){
                    content = content.concat(" (by: " + tasks.get(indexes.get(i)).getDetails() + ")");
                } else if(tasks.get(indexes.get(i)).getSymbol() == 'E'){
                    content = content.concat(" (at: " + tasks.get(indexes.get(i)).getDetails() + ")");
                }
                content = content.concat("\n");
            }
        }
        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
