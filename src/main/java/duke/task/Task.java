package duke.task;

/**
 * An abstract class meant to be inherited by the types of Tasks that Duke accepts
 * e.g., <code>todo</code>, <code>event</code>, <code>deadline</code> tasks.
 */
public abstract class Task {
    protected String taskName;
    boolean isCompleted;
    String details;
    TypeOfTask taskType;
    String tag;


    /**
     * Constructor for Task class.
     *
     * @param taskName    Information about the task e.g., <code>todo borrow books</code>
     *                    where the taskInformation is "borrow books"
     * @param isCompleted A boolean indicating if the task is completed
     */
    public Task(String taskName, boolean isCompleted, String tag) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
        this.tag = tag;
    }




    /**
     * Converts task to <code>String</code> that will be scanned into preset task.txt file.
     *
     * @return A String representing the task in the specified format
     */
    public String convertTaskToFileString() {
        char isCompleted = this.isCompleted ? 'Y' : 'N';

        String taskType = this.taskType.toString();
        // todo1 + " " + "" => todo1
        String taskNameAndDetails = (taskName + " " + details).stripTrailing();
        String result = String.format("%s|%c|%s|%s", taskType, isCompleted, taskNameAndDetails, this.tag);

//      String result = taskType + " | " + isCompleted + " | " + this.taskName + " " + this.details + " | " + ;
        return result;
    }

    /**
     * Mark task as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Get name of task.
     *
     * @return taskName in <code>String</code> format
     */
    public String getTaskName() {
        assert !taskName.isEmpty();
        return taskName;
    }

    /**
     * Get details of task.
     *
     * @return String representing extra details of task
     */
    public String getDetails() {
        return details;
    }

    public boolean getCompletionStatus() {
        return isCompleted;
    }

    /**
     * Get entire task represented in a String.
     *
     * @return String representing a task instance
     */
    @Override
    public String toString() {
        char isCompletedsymbol = this.isCompleted ? 'Y' : 'N';
        String taskNameAndDetails = (taskName + " " + details).stripTrailing();
        String result = "[" + taskType + "][" + isCompletedsymbol + "] " + taskNameAndDetails + " " + "[" + tag + "]";
        return result.stripTrailing();
    }
}
