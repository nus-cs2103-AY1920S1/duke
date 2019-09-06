package duke.person;

import java.util.ArrayList;

public class PersonList {
    private ArrayList<Person> list;

    public PersonList() {
        list = new ArrayList<>();
    }

    public PersonList(ArrayList<Person> list) {
        this.list = list;
    }

    public int getSize() {
        return list.size();
    }

    public Person addPerson(Person p) {
        list.add(p);
        return p;
    }

    public Person deletePerson(String name) {
        Person p = findPerson(name);
        if (p != null) {
            list.remove(p);
        }
        return p;
    }

    public Person findPerson(String name) {
        for (Person p : list) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public Person getPerson(int index) {
        return list.get(index);
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < this.getSize(); i++) {
            if (i != 0) {
                output = output + "\n";
            }
            output = output + this.getPerson(i).toString();
        }
        return output;
    }

    public String writer() {
        String text = "";
        for(int i = 0; i < this.getSize(); i++) {
            Person p = this.getPerson(i);
            if(i != 0) {
                text = text + "|";
            }
            text = text + p.toString();
        }
        return text;
    }
}
