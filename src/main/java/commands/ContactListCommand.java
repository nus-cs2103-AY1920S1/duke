package commands;

import logic.DukeException;
import logic.DukeList;
import logic.Storage;
import logic.Ui;

public class ContactListCommand extends ContactCommands {
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printList(tasks.getList(), "printTask");
    }
}
