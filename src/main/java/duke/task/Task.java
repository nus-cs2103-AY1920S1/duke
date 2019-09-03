package duke.task;

import java.util.Date;

/**
 * Class of task that divided into Deadline, Todo and Event.
 * @author   Yang Shuting
 * @see      Deadline
 * @see      Event
 * @see      Todo
 */
public class Task {
    protected  String description;
    protected boolean isDone;
    protected String tag = "";
    protected String information = "";
    protected static int noOfTask = 0;
    protected Date date;
    protected TaskType taskType;

    /**
     * Construct a task based on given description.
     * @param description String with valid information
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        noOfTask++;

    }

    /**
     * Construct Task from a string array that consist the type of fill and all the information.
     * @param input task from the storage
     */
    public Task(String[] input) {
        String type = input[0].trim();
        if (type.equals("T")) {
            this.description = input[2].trim();
            this.tag = "[T]";
            this.taskType = TaskType.TODO;
        } else if (type.equals("D")) {
            this.description = input[2].trim();
            this.information = input[3].trim();
            this.tag = "[D]";
            this.taskType = TaskType.DEADLINE;
        } else if (type.equals("E")) {
            this.description = input[2].trim();
            this.information = input[3].trim();
            this.tag = "[E]";
            this.taskType = TaskType.EVENT;

        }
        this.isDone = getStatusFromBit(input[1]);
        noOfTask++;

    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public int getStatusBit() {
        return isDone ? 1 : 0;
    }

    public boolean getStatusFromBit(String bit) {
        return bit.equals("1");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public static int getNoOfTask() {
        return noOfTask;
    }

    public static void reduceByOne() {
        noOfTask--;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getInformation() {
        return information;
    }

    @Override
    public String toString() {
        return tag + "[" + getStatusIcon() + "] "
                    + description + information;
    }
}
