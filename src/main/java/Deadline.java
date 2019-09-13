/**
 * This is the Deadline class. It contains TaskList objects that are assigned deadline.
 * @author Hua Lun
 */

public class Deadline extends TaskList {
    private DateAndTime by;

    /**
     * <p>
     *     This is the Deadline constructor.
     * </p>
     * @param taskNumber task number
     * @param taskCheck task status
     * @param taskName task name
     * @param type Deadline type
     * @param b due date for task
     */
    public Deadline(int taskNumber, String taskCheck, 
                    String taskName, String type, DateAndTime b) {
        super(taskNumber, taskCheck, taskName, type);
        by = b;
    }


    /**
     * <p>
     *     getAB is used to retrieve due date.
     * </p>
     * @return due date
     */
    public DateAndTime getAB() {
        return by;
    }

    /**
     * <p>
     *     Deadline toString to print out Deadline tasks.
     * </p>
     * @return Deadline task information
     */
    @Override
    public String toString() {
        return Integer.toString(getTaskNumber()) + ".[D]"
                + getTaskCheck() + " " + getTaskName() + by;
    }

}
