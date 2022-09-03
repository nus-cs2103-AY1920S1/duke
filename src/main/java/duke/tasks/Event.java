package duke.tasks;

import duke.DukeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The event subclass of the Task superclass. They have a instance eventTime which is when is this event.
 */
public class Event extends Task {

    /** Possible event times for this task. */
    private ArrayList<Date> eventTime = new ArrayList<>();

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Constructor of the class. The event time is taken as a string and transformed using a date format.
     *
     * @param taskName The task name.
     * @param eventTime the event time in the format "dd/MM/yyyy HH:mm:ss".
     * @throws DukeException If the task name is empty or the eventTime string is not in format "dd/MM/yyyy HH:mm:ss".
     */
    public Event(String taskName, String eventTime) throws DukeException {
        super(taskName);
        try {
            this.eventTime.add(dateFormatter.parse(eventTime));
        } catch (ParseException e) {
            throw new DukeException("The date input format is not correct, "
                    + "it should be in the form dd/MM/yyyy HH:mm:ss");
        }
    }

    /**
     * Get the information of the task FOR THE USER to see.
     * Output of this method is usually handled by Ui class.
     *
     * @return The information of the task, in form [type][finished] task name. For example, [T][X] Eat dinner.
     */
    @Override
    public String taskInfo() {
        String indicator;
        if (isFinished()) {
            indicator = "[V] ";
        } else {
            indicator = "[X] ";
        }
        String toReturn = "[E]" + indicator + getName() + " (at:";
        for (int i = 0; i < eventTime.size(); i++) {
            toReturn = toReturn + " \n  " + dateFormatter.format(eventTime.get(i));
        }
        toReturn = toReturn + ")";
        return toReturn;
    }

    /**
     * Get the information of the task FOR SAVING INTO A FILE.
     * Output of this method is usually handled by the task list.
     *
     * @return The information of the task, in form type|finished|task name. For example, T|0|Eat dinner.
     */
    @Override
    public String recordInfo() {
        String toReturn;
        if (isFinished()) {
            toReturn = "E|" + "1|" + getName() + "|";
        } else {
            toReturn = "E|" + "0|" + getName() + "|";
        }
        for (Date possibleTime : eventTime) {
            toReturn = toReturn + dateFormatter.format(possibleTime) + "%";
        }
        return toReturn;
    }

    /**
     * This method add another possible event time to the task.
     *
     * @param s The string about the possible event time in the format of "dd/MM/yyyy HH:mm:ss".
     * @throws DukeException If the event time is not in the format of "dd/MM/yyyy HH:mm:ss".
     */
    public void addEventTime(String s) throws DukeException {
        try {
            eventTime.add(dateFormatter.parse(s));
        } catch (ParseException e) {
            throw new DukeException("The date input format is not correct, "
                    + "it should be in the form dd/MM/yyyy HH:mm:ss");
        }
    }

    /**
     * This method specifies the event time, which is make the event time from multiple to unique.
     *
     * @param s The final decided event time.
     * @throws DukeException If the event time is not in the format of "dd/MM/yyyy HH:mm:ss".
     */
    public void specifyEventTime(String s) throws DukeException {
        try {
            eventTime.clear();
            eventTime.add(dateFormatter.parse(s));
        } catch (ParseException e) {
            throw new DukeException("The date input format is not correct, "
                    + "it should be in the form dd/MM/yyyy HH:mm:ss");
        }
    }
}
