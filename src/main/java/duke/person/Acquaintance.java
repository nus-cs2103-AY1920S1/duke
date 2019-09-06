package duke.person;

public class Acquaintance {
    PersonList list;

    public Acquaintance() {
        list = new PersonList();
    }

    public Acquaintance(PersonList list) {
        this.list = list;
    }

    public Person findPerson(String name) {
        return list.findPerson(name);
    }

    public String findTitle(String name) {
        return list.findPerson(name).getTitle();
    }

    public int findContact(String name) {
        return list.findPerson(name).getContact();
    }
}
