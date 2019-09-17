/**
 * Todo is a subclass of a Task.
 * Todo represents a task with no deadline, and only
 * contains a decription of what is to be done.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object.
     * @param description Description of the task.
     */
    public Todo(String description, Priority priority) {
        super(description, priority);
    }

    /**
     * Constructs a Todo object.
     * This constructor is for when it is being loaded from memory
     * and may have already been completed, so its isDone status must
     * be updated accordingly.
     * @param description Description of the task
     * @param status Status of completion
     */
    public Todo(String description, boolean status, Priority priority) {
        super(description, priority);
        this.isDone = status;
    }

    /**
     * Converting to a format to be stored on file.
     * Task is converted to a string that is stored on the
     * hard disk, and can be read easily when loaded so that
     * the information can be loaded onto the Task List when the
     * program first starts.
     * @return String Formatted string to be stored.
     */
    @Override
    public String toFileFormat() {
        StringBuilder fileFormat = new StringBuilder();

        fileFormat.append(taskPriority.toString() + "~");
        fileFormat.append("T~");

        if (this.isDone) {
            fileFormat.append("1~");
        } else {
            fileFormat.append("0~");
        }

        fileFormat.append(this.description);

        return fileFormat.toString();
    }

    @Override
    public String toString() {
        String task = "[T][" + this.getStatusIcon() +  "] " + description;
        return task;
    }
}