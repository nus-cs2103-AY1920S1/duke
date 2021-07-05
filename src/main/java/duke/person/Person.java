package duke.person;

public class Person {
    private String name;
    private String title;
    private String contact;

    public Person(String name) {
        this(name, "", "N.A.");
    }

    public Person(String name, String title) {
        this(name, title, "N.A.");
    }

    /**
     * constructor for Person.
     *
     * @param name    String of person's name
     * @param title   person's title
     * @param contact the person's contact number
     */
    public Person(String name, String title, String contact) {
        this.name = name;
        this.title = title;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return this.name + "(" + this.title + ")" + this.contact;
    }
}
