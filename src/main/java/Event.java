public class Event extends TaskList {
    private DateAndTime at;

    /**
     * <p>
     *     This is the Event constructor.
     * </p>
     */
  
    public Event(int taskNumber, String taskCheck, 
                 String taskName, String type, DateAndTime t) {
        super(taskNumber, taskCheck, taskName, type);
        at = t;
    }

    /**
     * <p>
     *     getAB is used to retrieve event date.
     * </p>
     * @return event date
     */

    public DateAndTime getAB() {
        return at;
    }

    /**
     * <p>
     *     Event toString to print out event tasks.
     * </p>
     * @return Event task information
     */
    @Override
    public String toString() {
        return Integer.toString(getTaskNumber()) + ".[E]"
                + getTaskCheck() + getTaskName() + "at " + at;
    }
}
