package Logic;

import Model.Tasklist;
import Storage.Storage;
import UserInterface.UI;

public class WrongCommand implements Command {
    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage) {
        String content = "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        return content;
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
