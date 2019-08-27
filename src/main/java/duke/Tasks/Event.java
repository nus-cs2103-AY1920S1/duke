package duke.Tasks;

import duke.DukeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The event subclass of the Task superclass. They have a instance eventTime which is when is this event.
 * @Extends duke.Tasks.Task
 */
public class Event extends Task{

    /** When is this task. */
    private Date eventTime;
    SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Constructor of the class. The event time is taken as a string and transformed using a date format.
     * @param taskName The task name.
     * @param eventTime the event time.
     * @throws DukeException If the task name is empty or the eventTime string is not in format "dd/MM/yyyy HH:mm:ss".
     */
    public Event(String taskName, String eventTime) throws DukeException {
        super(taskName);
        try {
            this.eventTime = myFormat.parse(eventTime);
        } catch (ParseException e) {
            throw new DukeException("The date input format is not correct, " +
                    "it should be in the form dd/MM/yyyy HH:mm:ss");
        }
    }

    /**
     * This method returns the information of the task FOR THE USER to see.
     * Output of this method is usually handled by Ui class.
     * @return The information of the task, in form [type][finished] task name. For example, [T][X] Eat dinner.
     */
    @Override
    public String taskInfo() {
        String indicator;
        if (isFinished()) indicator = "[\u2713] ";
        else indicator = "[\u2715] ";
        return "[E]" + indicator + getName() + " (at: " + myFormat.format(eventTime) + ")";
    }

    /**
     * This method returns the information of the task FOR SAVING INTO A FILE.
     * Output of this method is usually handled by the task list.
     * @return The information of the task, in form type|finished|task name. For example, T|0|Eat dinner.
     */
    @Override
    public String recordInfo() {
        if (isFinished()) return "E|" + "1|" + getName() + "|" + myFormat.format(eventTime);
        else return "E|" + "0|" + getName() + "|" + myFormat.format(eventTime);
    }
}
