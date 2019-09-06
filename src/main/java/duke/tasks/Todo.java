package duke.tasks;

import duke.person.PersonList;

public class Todo extends Task {

    public Todo(String description, PersonList list) {
        super(description, list);
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString()
                + "\nList of People:\n" + super.getPeople().toString();
    }

    @Override
    public String writer() {
        String text = "T | ";
        if (getStatus() == false) {
            text = text.concat("0 | " + getDescription());
        } else {
            text = text.concat("1 | " + getDescription());
        }
        text = text + " |" + super.getPeople().writer();
        return text;
    }
}
