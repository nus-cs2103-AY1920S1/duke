/**
 * Represents a task with a deadline
 * Contains a description of the task
 * Contains the time of the deadline
 */


public class Deadline extends Task {
    protected String time;

    public Deadline (String descriptionAndTime) {
        super(descriptionAndTime);
        String[] splitString = descriptionAndTime.split("/by");
        this.description = splitString[0].substring(9, splitString[0].length() - 1);
        this.time = splitString[1].substring(1, splitString[1].length());
    }

    @Override
    public String toString() {
        String output = "[D][" + this.getStatusIcon() + "] " + this.description;
        output += " (by: " + this.time + ")";
        return output;
    }
}
