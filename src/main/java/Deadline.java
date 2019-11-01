import java.time.LocalDateTime;

/**
 * Deadline is a subclass of Task.
 * Deadline represents a task with a description and a
 * corresponding deadline.
 */
public class Deadline extends Task {
    private String deadlineString;
    private LocalDateTime deadlineDate;

    /**
     * Constructs a Deadline object.
     * @param description Description of the task.
     * @param deadline deadline of the task.
     */
    public Deadline(String description, String deadline, Priority priority) {
        super(description, priority);

        this.deadlineString = makeDeadline(deadline);
        this.deadlineDate = storeAsDateTime(deadlineString);
    }

    /**
     * Constructs a Deadline object.
     * This constructor is for when the Deadline is being loaded
     * from memory and hence can be already completed and the
     * status of completion needs to be a parameter as well.
     * @param description Description of the task
     * @param deadline Date of the deadline
     * @param status Status of completion
     */
    public Deadline(String description, String deadline, boolean status, Priority priority) {
        super(description, priority);
        this.deadlineString = makeDeadline(deadline);
        this.deadlineDate = storeAsDateTime(deadlineString);
        this.isDone = status;
    }

    /**
     * Formats the String deadline.
     * @param deadline Unformatted String deadline
     * @return String Formatted String deadline
     */
    public String makeDeadline(String deadline) {
        StringBuilder temp = new StringBuilder();
        String[] deadlineArr = deadline.split(" ");

        temp.append(deadlineArr[0]);
        if (!deadlineArr[0].contains(":")) {
            temp.append(":");
        }

        for (int i = 1; i < deadlineArr.length; i++) {
            temp.append(" ");
            temp.append(deadlineArr[i]);
        }

        return temp.toString();
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
        fileFormat.append("D~");

        if (this.isDone) {
            fileFormat.append("1~");
        } else {
            fileFormat.append("0~");
        }

        fileFormat.append(this.description);
        fileFormat.append("~");
        fileFormat.append(this.deadlineString);

        return fileFormat.toString();
    }

    @Override
    public String toString() {
        String task = "[D][" + this.getStatusIcon() + "] " + description + " (" + deadlineString + ")";
        return task;
    }
}