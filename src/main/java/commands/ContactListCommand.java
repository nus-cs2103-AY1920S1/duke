package commands;

import logic.DukeList;
import logic.Storage;
import logic.Ui;

public class ContactListCommand extends ContactCommands {
    @Override
    public void execute(DukeList list, Ui ui, Storage storage) {
        ui.printList(list.getList(), "printContacts");
    }
}
