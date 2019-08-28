/**
 * This is the to-do class. It contains TaskList objects that are assigned to-do.
 * @author Hua Lun
 */
public class Todo extends TaskList {

    /**
     * <p>
     *     This is the to-do constructor.
     * </p>
     * @param taskNumber task number
     * @param taskCheck task status
     * @param taskName task name
     * @param type to-do type of task
     */
    public Todo(int taskNumber, String taskCheck, 
                String taskName, String type) {
        super(taskNumber, taskCheck, taskName, type);
    }

    /**
     * <p>
     *     getAB is a filler.
     * </p>
     * @return nil
     */

    public DateTime getAB() {
        return null;
    }

    /**
     * <p>
     *     to-do toString to print out to-do tasks
     * </p>
     * @return to-do task information
     */
    @Override
    public String toString() {
        return  Integer.toString(getTaskNumber()) + ".[T]" +
                getTaskCheck() + " " + getTaskName();
    }
}
