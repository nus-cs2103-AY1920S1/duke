package seedu.duke.tasks;

import seedu.duke.tasks.exceptions.InvalidTaskException;

import java.util.Date;

public abstract class Task {
    private Long taskID;
    private TaskType type;
    private boolean isDone;
    private Date createdAt;
    private Date updatedAt;
    private String description;

    /**
     * Constructor for <code>Task</code> object.
     *
     * @param taskID      Task ID of the task
     * @param type        Type of the task.
     * @param description Description of the task.
     * @param isDone      A boolean that indicates whether the task has been done or not.
     * @param createdAt   Creation time
     * @param updatedAt   Last update time
     */
    Task(long taskID, TaskType type, boolean isDone, Date createdAt, Date updatedAt, String description) {
        this.taskID = taskID;
        this.type = type;
        this.isDone = isDone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.description = description;
    }

    /**
     * Convenient constructor for <code>Task</code> object. By default, a task will have:
     * - A random ID that still does not exist in the list of tasks stored by Duke chat bot.
     * - An unfinished status
     * - Created and updated time equal to the object initialization time.
     *
     * @param type        Type of the task.
     * @param description Description of the task.
     */
    Task(TaskType type, String description) {
        taskID = TaskIdGenerator.shared.generateAndRegisterNewID();
        this.type = type;
        isDone = false;
        createdAt = updatedAt = new Date();
        this.description = description;
    }

    /**
     * Parser for Task object. It converts the <code>String</code> representation of a Task that is used to
     * store it in the database into a <code>Task</code> object.
     *
     * @param str A <code>String</code> representation of a <code>Task</code> which is stored in the file storage.
     * @return A <code>Task</code> object that corresponds to the <code>String</code>
     * @throws InvalidTaskException An error that causes failure in the parsing process, e.g. inconsistent format.
     */
    static Task parseTask(String str) throws InvalidTaskException {
        return TaskParser.parseTask(str);
    }

    public boolean equals(Task other) {
        return type == other.type && description.equals(other.description);
    }

    public Long getTaskID() {
        return taskID;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[" + taskID + "][" + type.getTaskIcon() + "][" + getStatusIcon() + "] " + getDescription();
    }

    public void markAsDone() {
        isDone = true;
        updatedAt = new Date();
    }

    String getDataRepresentation() {
        return taskID + " | " + type.getTaskIcon() + " | " + getStatusIcon() + " | " + createdAt
                + " | " + updatedAt + " | " + description;
    }

    private String getStatusIcon() {
        return (isDone ? "O" : "X");
    }
}
