package duke.tasks;

import duke.person.PersonList;

public class Event extends Task {
    protected String at;
    protected String day;
    protected String month;
    protected String year;
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

    /**
     * constructor for event.
     *
     * @param description task description
     * @param at          time that event is held at
     * @param list        list of people related to the event
     */
    public Event(String description, String at, PersonList list) {
        super(description, list);
        this.at = at;
        parseAt(at);
    }

    private void parseAt(String at) {
        if (at.contains("/")) {
            String[] parts = at.split("[/]");
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

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")"
                + "\nList of People:\n" + super.getPersonList().toString();
    }

    @Override
    public String writer() {
        String text = "E|";
        if (getStatus() == false) {
            text = text.concat("0|" + getDescription() + "|" + getAt());
        } else {
            text = text.concat("1|" + getDescription() + "|" + getAt());
        }
        text = text + "|" + super.getPersonList().writer();
        return text;
    }
}
