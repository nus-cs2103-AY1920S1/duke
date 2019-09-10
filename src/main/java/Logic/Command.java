package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public interface Command {

    void execute(Tasklist tasks, UI ui, Storage storage);

    boolean isExit();

}
