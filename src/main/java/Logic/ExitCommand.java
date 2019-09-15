package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public class ExitCommand implements Command{

    public ExitCommand(){

    }

    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage){
        String content = "Bye. Hope to see you again soon!";
        return content;
    }

    @Override
    public boolean isExit(){
        return true;
    }

}
