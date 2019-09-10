package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public class WrongCommand implements Command {
    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        ui.printData("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
