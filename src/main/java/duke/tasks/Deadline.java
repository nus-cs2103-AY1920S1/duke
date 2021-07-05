package duke.tasks;

import duke.person.PersonList;

public class Deadline extends Task {
    protected String by;
    protected String day;
    protected String month;
    protected String year;
    protected String time;

    /**
     * constructor for deadline type task.
     *
     * @param description task description
     * @param by          time by which the task should be done
     * @param list        list of people who are related to the task
     */
    public Deadline(String description, String by, PersonList list) {
        super(description, list);
        this.by = by;
        parseBy(by);
    }

    private void parseBy(String by) {
        if (by.contains("/")) {
            String[] parts = by.split("[/]");
            assert parts.length == 3 : "time format is wrong";
            day = parts[0];
            month = parts[1];
            String[] subParts = parts[2].split(" ");
            year = subParts[0];
            time = subParts[1];
        } else {
            day = "";
            month = "";
            year = "";
            time = "";
        }
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
