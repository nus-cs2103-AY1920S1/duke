package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public class ListCommand implements Command {

    public ListCommand(){

    }

    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage){
        int i;

        String content = "";
        System.out.println(tasks.size());

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
