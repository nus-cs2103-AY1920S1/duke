/**
 * Encapsulates the Task object.
 * The Task object includes its own description
 * as well as if any of the object instantiated is done or uncompleted.
 * Also, it keep tracks of a counter of all Task objects that are instantiated.
 * The Task object provides the extension to other children classes such as Todo, Deadline and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    String typeOfTask = "";
    protected static int taskCount = 0;

    /**
     * Constructs a Task object with a specific description of a task that has to be completed.
     * @param description This is a brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        if (!description.equals("")) {
            taskCount += 1;
        }
    }

    /**
     *Returns the status of the Task with a tick or cross icon if the task is done or incomplete respectively.
     * @return a tick or cross icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the Task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the number of Task objects that exist. This is a getter method.
     * @return the number of Task objects that have been created.
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Performs the reduction of the number of Task that has been created by 1. This is method is
     * needed when a Task is destroyed and the Task counter has to be updated.
     */
    public static void reduceTaskCount() {
        taskCount -= 1;
    }

    public String getTypeOfTask() {
        return this.typeOfTask;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Convert to standard string format
     * @return a message that reflects the type and description of the Task.
     */
    public String toString() {
        String statusIcon = this.getStatusIcon();
        return  ("[" + typeOfTask + "]" + "[" + statusIcon + "] "
                + description);
    }
}
