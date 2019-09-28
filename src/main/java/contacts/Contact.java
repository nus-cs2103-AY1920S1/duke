package contacts;

public class Contact {
    private final String name;
    private String relationship; //my relationship to that person
    private String contactNumber;
    private String email;

    /**
     * Constructor for Contact Object.
     *
     * @param name          Name of Contact
     * @param relationship  My r/s with the contact
     * @param contactNumber Contact Number of contact
     * @param email         Email of contact
     */
    private Contact(String name, String relationship, String contactNumber, String email) {
        this.name = name;
        this.relationship = relationship;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public Contact(String[] contactDetails) {
        this(contactDetails[0], contactDetails[1], contactDetails[2], contactDetails[3]);
    }

    public String getName() {
        return name;
    }

    /**
     * Returns String to be written to file later
     * Example: LoremIpsum | 01/01/1997 | FakePerson | 94350294 | loremipsum@gmail.com
     *
     * @return
     */
    public String toFileString() {
        String div = " | ";
        StringBuilder sb = new StringBuilder(name);
        sb.append(div);
        sb.append(relationship);
        sb.append(div);
        sb.append(contactNumber);
        sb.append(div);
        sb.append(email);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + name + "\n");
        sb.append("Relationship: " + relationship + "\n");
        sb.append("Contact Number: " + contactNumber + "\n");
        sb.append("Email: " + email + "\n");
        return sb.toString();
    }
}
