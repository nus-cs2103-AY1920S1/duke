package duke.tasks;

import duke.person.PersonList;

public class Task {
    private String description;
    private boolean isDone;
    private PersonList personList;

    public Task() {
        this("", new PersonList());
    }

    /**
     * constructor of Task.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this(description, new PersonList());
    }

    public Task(String description, PersonList list) {
        this.description = description;
        this.isDone = false;
        this.personList = list;
    }

    public void addPeople(PersonList list) {
        for (int i = 0; i < list.getSize(); i++) {
            personList.addPerson(list.getPerson(i));
        }
    }

    public String getStatusIcon() {
        return (isDone ? "+" : "-"); //return tick or X symbols
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void changeStatus() {
        isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }

    public String writer() {
        return "This is a task";
    }

    public PersonList getPersonList() {
        return personList;
    }
}
