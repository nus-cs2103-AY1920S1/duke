package duke.Tasks;

import duke.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The deadline subclass of the Task superclass. They have a instance deathTime which is the due time.
 * @Extends duke.Tasks.Task
 */
public class Deadline extends Task {

    /** The deadline of the task. */
    private Date deathTime;
    SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * Constructor of the class. The deadline is taken as a string and transformed using a date format.
     * @param taskName The task name.
     * @param deathTime the deadline.
     * @throws DukeException If the task name is empty or the deathTime string is not in format "dd/MM/yyyy HH:mm:ss".
     */
    public Deadline(String taskName, String deathTime) throws DukeException {
        super(taskName);
        try {
            this.deathTime = myFormat.parse(deathTime);
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
        return "[D]" + indicator + getName() + " (by: " + myFormat.format(deathTime) + ")";
    }

    /**
     * This method returns the information of the task FOR SAVING INTO A FILE.
     * Output of this method is usually handled by the task list.
     * @return The information of the task, in form type|finished|task name. For example, T|0|Eat dinner.
     */
    @Override
    public String recordInfo() {
        if (isFinished()) return "D|" + "1|" + getName() + "|" + myFormat.format(deathTime);
        else return "D|" + "0|" + getName() + "|" + myFormat.format(deathTime);
    }
}
