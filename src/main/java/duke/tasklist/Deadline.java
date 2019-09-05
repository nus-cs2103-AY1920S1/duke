package duke.tasklist;

import duke.date.Date;
import duke.tasklist.Task;

public class Deadline extends Task {
    Date time;
    /**
     * Dummy javadocs method.
     */

    public Deadline(String description, String time) {
        super(description);
        String[] dateAndTime = time.split(" ");
        String[] date = dateAndTime[0].split("/");
        this.time = new Date(date[0], date[1], date[2], dateAndTime[1]);
    }

    public String getTime() {
        return time.toString();
    }

    @Override
    public String saveString() {
        String saveString = "D | ";

        if (this.isCompleted()) {
            saveString += 1;
        } else {
            saveString += 0;
        }

        saveString += " | ";
        saveString += this.getDescription();
        saveString += " | ";
        saveString += this.getTime();
        saveString += "\n";

        return saveString;
    }

    @Override
    public String toString() {
        return "[D][" + this.getMark() + "] " + this.getDescription() + " (by: " + time + ")\n";
    }
}
