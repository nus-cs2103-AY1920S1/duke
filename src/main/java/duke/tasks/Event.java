package duke.tasks;

import duke.person.PersonList;

public class Event extends Task {
    protected String at;
    protected String year;
    protected String month;
    protected String day;
    protected String time;

    /**
     * creates an event task.
     *
     * @param description description of the task
     * @param at          time information
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        parseAt(at);
    }

    public Event(String description, String at, PersonList list) {
        super(description, list);
        this.at = at;
        parseAt(at);
    }

    private void parseAt(String str) {
        String[] splits = str.split("[/ ]");
        day = splits[0];
        month = splits[1];
        year = splits[2];
        time = splits[3];
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")"
                + "\nList of People:\n" + super.getPeople().toString();
    }

    @Override
    public String writer() {
        String text = "E | ";
        if (getStatus() == false) {
            text = text.concat("0 | " + getDescription() + " | " + getAt());
        } else {
            text = text.concat("1 | " + getDescription() + " | " + getAt());
        }
        text = text + " |" + super.getPeople().writer();
        return text;
    }
}
