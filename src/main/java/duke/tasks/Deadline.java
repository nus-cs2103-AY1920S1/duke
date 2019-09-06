package duke.tasks;

import duke.person.PersonList;

public class Deadline extends Task {
    protected String by;
    protected String year;
    protected String month;
    protected String day;
    protected String time;

    /**
     * constructor of Deadline task.
     *
     * @param description description of the task
     * @param by          time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        parseBy(by);
    }

    public Deadline(String description, PersonList list) {
        super(description, list);
        this.by = by;
        parseBy(by);
    }

    private void parseBy(String str) {
        String[] splits = str.split("[/ ]");
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
                + "\nList of People:\n" + super.getPeople().toString();
    }

    @Override
    public String writer() {
        String text = "D | ";
        if (getStatus() == false) {
            text = text.concat("0 | " + getDescription() + " | " + getBy());
        } else {
            text = text.concat("1 | " + getDescription() + " | " + getBy());
        }
        text = text + " |" + super.getPeople().writer();
        return text;
    }
}
