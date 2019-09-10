package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public class ExitCommand implements Command{

    public ExitCommand(){

    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage){
        ui.printData("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit(){
        return true;
    }

}
