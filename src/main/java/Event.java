/**
 * Represents an event to be completed
 * Contains a description of the task
 * Contains the time of the event
 */


public class Event extends Task {
    protected String time;

    public Event (String descriptionAndTime) {
        super(descriptionAndTime);
        this.type = "E";
        String[] splitString = descriptionAndTime.split("/at");
        this.description = splitString[0].substring(6, splitString[0].length() - 1);
        this.time = splitString[1].substring(1);
    }

    @Override
    public String getTime() {
        return (" /at " + this.time + " |");
    }


    @Override
    public String toString() {
        String output = "[E][" + this.getStatusIcon() + "] " + this.description;
        output += " (at: " + this.time + ")";
        return output;
    }
}
