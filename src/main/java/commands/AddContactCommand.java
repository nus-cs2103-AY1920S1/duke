package commands;

import contacts.Contact;
import logic.DukeException;
import logic.DukeList;
import logic.DukeStrings;
import logic.Storage;
import logic.Ui;

public class AddContactCommand extends ContactCommands {
    private String args;

    public AddContactCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(DukeList list, Ui ui, Storage storage) throws DukeException {
        String[] contactDetails = args.split(", ");

        if (args.trim().isEmpty()) {
            throw new DukeException(DukeStrings.CONTACT_EMPTY);
        } else if (contactDetails.length != 4) {
            throw new DukeException(DukeStrings.CONTACT_WRONG_FORMAT);
        }

        Contact contact = new Contact(contactDetails); //args is the description string
        list.add(contact);
        storage.updateContactFile(list);
    }
}
