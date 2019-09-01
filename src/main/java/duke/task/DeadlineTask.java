package duke.task;

import duke.constant.Consts;
import duke.exception.WrongDateFormatException;

import java.text.ParseException;

/**
 * Represents a deadline task in the application extends the Task class.
 */
public class DeadlineTask extends Task {

    /**
     * Initialises an deadline task with the description and date and time of the deadline task.
     *
     * @param description Description of the task.
     * @param deadline String of datetime of the deadline task.
     * @throws WrongDateFormatException Thrown when the String deadline is of the wrong format.
     */
    public DeadlineTask(String description, String deadline) throws WrongDateFormatException {
        super(description);
        try {
            this.dateTime = Consts.DATE_TIME_FORMATTER.parse(deadline);
        } catch (ParseException e) {
            throw new WrongDateFormatException();
        }
        this.type = "D";
    }

    /**
     * Initialises an deadline task with the description, date and time and a boolean representing
     * the completion state of the task.
     *
     * @param description Description of the task.
     * @param deadline String of datetime of the deadline task.
     * @param isDone Boolean representing the completion state of the task.
     * @throws WrongDateFormatException Thrown when the String deadline is of the wrong format.
     */
    public DeadlineTask(String description, String deadline, boolean isDone) throws WrongDateFormatException {
        super(description, isDone);
        try {
            this.dateTime = Consts.DATE_TIME_FORMATTER.parse(deadline);
        } catch (ParseException e) {
            throw new WrongDateFormatException();
        }
        this.type = "D";
    }

    /**
     * Returns A string that includes the type task and the toString of Task.
     *
     * @return String that adds the type of the task to the toString method of Task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Consts.DATE_TIME_OUTPUT_FORMATTER.format(dateTime));
    }
}
