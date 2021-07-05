package duke.tasks;

import duke.person.PersonList;

public class Task {
    private static final String outputTask = "This is a task";
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

    /**
     * constructor of task.
     *
     * @param description task description
     * @param list list of people related to the task
     */
    public Task(String description, PersonList list) {
        this.description = description;
        this.isDone = false;
        this.personList = list;
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
        return outputTask;
    }

    public PersonList getPersonList() {
        return personList;
    }
}
