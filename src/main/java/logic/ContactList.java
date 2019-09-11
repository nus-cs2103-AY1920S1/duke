package logic;

import contacts.Contact;

import java.util.List;
import java.util.stream.Collectors;

public class ContactList implements DukeList<Contact> {
    private List<Contact> contactList;

    public ContactList(List<Contact> taskList) {
        this.contactList = taskList;
    }

    @Override
    public List<Contact> getList() {
        return contactList;
    }

    /**
     * Adds Contacts to Contact List.
     *
     * @param contact Contact Obj to be added
     */
    @Override
    public void add(Contact contact) {
        assert contact != null;
        contactList.add(contact);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this contact:\n");
        sb.append(contact + "\n");
        Ui.loadStr(sb.toString());
    }

    /**
     * Find and filter contacts by keyword.
     *
     * @param keyword Keyword string to filter with
     * @return Filtered list of tasks
     */
    @Override
    public List<Contact> find(String keyword) {
        return contactList.stream()
                .filter(contact -> contact.getName().contains(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String num) throws DukeException {
        int contactNum = Parser.parseInt(num, contactList);
        StringBuilder sb = new StringBuilder();
        Contact task = contactList.get(contactNum - 1);
        assert task != null;
        sb.append("Noted. I've removed this contact: \n");
        sb.append(task + "\n");
        contactList.remove(contactNum - 1);
        sb.append("Now you have " + contactList.size() + " contacts in your contact list.");
        Ui.loadStr(sb.toString());
    }
}
