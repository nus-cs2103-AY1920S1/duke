package commands;

import logic.DukeException;
import logic.DukeList;
import logic.Storage;
import logic.Ui;

public class AddContactCommand extends Command {
    private String args;

    public AddContactCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {

    }
}
