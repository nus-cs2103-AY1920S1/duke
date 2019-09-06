package duke.tasks;

import duke.person.PersonList;

public class Deadline extends Task {
    protected String by;
    protected String year;
    protected String month;
    protected String day;
    protected String time;

    /**
     * constructor for deadline type task.
     *
     * @param description task description
     * @param by time by which the task should be done
     * @param list list of people who are related to the task
     */
    public Deadline(String description, String by, PersonList list) {
        super(description, list);
        this.by = by;
        parseBy(by);
    }

    private void parseBy(String str) {
        String[] splits = str.split("[/ ]");
        assert splits.length >= 4 : "Time is invalid";
        day = splits[0];
        month = splits[1];
        year = splits[2];
        time = splits[3];
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")"
                + "\nList of People:\n" + super.getPersonList().toString();
    }

    @Override
    public String writer() {
        String text = "D|";
        if (getStatus() == false) {
            text = text.concat("0|" + getDescription() + "|" + getBy());
        } else {
            text = text.concat("1|" + getDescription() + "|" + getBy());
        }
        text = text + "|" + super.getPersonList().writer();
        return text;
    }
}
