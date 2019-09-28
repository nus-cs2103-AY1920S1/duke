package duke.task;

import duke.command.Parser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a Deadline object that contains a description and time by which it must be completed.
 */
public class Deadline extends Task {
    private Calendar time;

    /**
     * Creates a Deadline object using information from the arrayList of the Parser.
     * @param parser parser which is used to get information to make the obejct.
     */
    public Deadline(Parser parser) {
        super(parser.getList().get(0));
        time = Calendar.getInstance();
        ArrayList<String> inputArray = parser.getList();
        time.set(Integer.parseInt(inputArray.get(3)),
                (Integer.parseInt(inputArray.get(2)) - 1),
                Integer.parseInt(inputArray.get(1)),
                Integer.parseInt(inputArray.get(4)),
                Integer.parseInt(inputArray.get(5)));
    }

    /**
     * Creates a deadline object using description and time at which Deadline to be done by.
     * @param des string to represent description of the Task.
     * @param time time that deadline must be completed by.
     */
    public Deadline(String des, Calendar time) {
        super(des);
        this.time = time;
    }

    /**
     * Return time by which deadline to be completed.
     * @return time by which deadline should be completed.
     */
    public Calendar getTime() {
        return time;
    }

    /**
     * Prints time of the deadline in a user-friendly readable format.
     * @return string representing time of the deadline in a particular format.
     */
    public String printTime() {
        return printDateAndTime(time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.printTime() +  ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deadline)) {
            return false;
        } else {
            return this.getDescription().equals(((Deadline) o).getDescription())
                    && equal(this.time, ((Deadline) o).time);
        }
    }
}
