package commands;

import logic.DukeException;
import logic.DukeList;
import logic.Storage;
import logic.Ui;

public class DeleteContactCommand extends ContactCommands {
    private String args;

    public DeleteContactCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(DukeList list, Ui ui, Storage storage) throws DukeException {
        list.delete(args); //deletes contact obj
        storage.updateContactFile(list);
    }
}
