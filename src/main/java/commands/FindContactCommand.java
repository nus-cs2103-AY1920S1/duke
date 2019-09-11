package commands;

import logic.DukeList;
import logic.Storage;
import logic.Ui;
import task.Task;

import java.util.List;

/**
 * Find Contacts by name.
 */
public class FindContactCommand extends ContactCommands {
    private String args;

    public FindContactCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(DukeList list, Ui ui, Storage storage) {
        List<Task> filteredContacts = list.find(args);
        ui.printList(filteredContacts, "printFilteredContacts");
    }
}
