package duke.person;

public class Person {
    private String name;
    private String title;
    private int contact;

    public Person(String name, String title, int contact) {
        this.name = name;
        this.title = title;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public int getContact() {
        return contact;
    }

    public String getTitle() {
        return title;
    }

    public void modifyName(String name){
        this.name = name;
    }

    public void modifyTitle(String title) {
        this.title = title;
    }

    public void modifyContact(int contact) {
        this.contact = contact;
    }

    public String toString() {
        return this.name+"("+this.title+") "+this.contact;
    }
}
