package duke.task;

import duke.command.Parser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents an duke.task.Event object that has a description, start-time and end-time to depict a duration.
 */
public class Event extends Task {
    private Calendar startTime;
    private Calendar endTime;

    /**
     * Creates duke.task.Event object taking info from Parser's ArrayList.
     * @param parser Parser containing the information to create the new Event object
     */
    public Event(Parser parser) {
        super(parser.getList().get(0));
        startTime = Calendar.getInstance();
        endTime = Calendar.getInstance();
        ArrayList<String> inputArray = parser.getList();
        startTime.set(Integer.parseInt(inputArray.get(3)),
                (Integer.parseInt(inputArray.get(2)) - 1),
                Integer.parseInt(inputArray.get(1)),
                Integer.parseInt(inputArray.get(4)),
                Integer.parseInt(inputArray.get(5)));
        endTime.set(Integer.parseInt(inputArray.get(3)),
                (Integer.parseInt(inputArray.get(2)) - 1),
                Integer.parseInt(inputArray.get(1)),
                Integer.parseInt(inputArray.get(6)),
                Integer.parseInt(inputArray.get(7)));
    }

    /**
     * Creates duke.task.Event object using description, start, and end-time.
     * @param des description of Task.
     * @param startTime startTime of duke.task.Event.
     * @param endTime endTime of duke.task.Event.
     */
    public Event(String des, Calendar startTime, Calendar endTime) {
        super(des);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Return startTime of event.
     * @return startTime.
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Return endTime of event.
     * @return endTime.
     */
    public Calendar getEndTime() {
        return endTime;
    }

    /**
     * Returns a string representing startTime in a particular user-friendly format.
     * @return string representing the startTime in a printing friendly format.
     */
    public String printStartTime() {
        return printDateAndTime(startTime);
    }
    
    /**
     * Returns a string representing endTime in a particular user-friendly format.
     * @return string representing the endTime in a printing friendly format.
     */
    public String printEndTime() {
        return printDateAndTime(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.printStartTime() + " - "
                + this.printEndTime()
                + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Event)) {
            return false;
        } else {
            return this.getDescription().equals(((Event) o).getDescription())
                    && equal(this.startTime, ((Event) o).startTime)
                    && equal(this.endTime, ((Event) o).endTime);
        }
    }
}
