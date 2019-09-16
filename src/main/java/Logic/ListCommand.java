package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public class ListCommand implements Command {

    /**
     * Creates an instance of ListCommand
     */
    public ListCommand(){

    }

    /**
     * Parses the arguments of the Command and executes it
     * @param tasks     the TaskList of Tasks
     * @param ui        The User Interface
     * @param storage   Storage
     * @return
     */
    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage){
        int i;
        String content = "";

        for(i = 0; i < tasks.size(); i++){
            content = content.concat((i + 1) + ". ");
            content = content.concat("[" + tasks.get(i).getSymbol() + "]");
            content = content.concat("[" + tasks.get(i).getIsDoneSymbol() + "]");
            content = content.concat(" " + tasks.get(i).getDescription());
            if(tasks.get(i).getSymbol() == 'D'){
                content = content.concat(" (by: " + tasks.get(i).getDetails() + ")");
            } else if(tasks.get(i).getSymbol() == 'E'){
                content = content.concat(" (at: " + tasks.get(i).getDetails() + ")");
            }
            content = content.concat("\n");
        }
        storage.save(tasks);

        return content;
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
