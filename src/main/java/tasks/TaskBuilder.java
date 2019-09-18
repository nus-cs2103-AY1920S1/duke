package tasks;

import java.util.Date;

// TODO: 29/8/2019 Validation of required fields before building 
public class TaskBuilder {
    private String description;
    private Date deadline;
    private Date timeframe;
    private TaskType type;

    public TaskBuilder type(TaskType t) {
        this.type = t;
        return this;
    }

    public TaskBuilder description(String d) {
        description = d;
        return this;
    }

    public TaskBuilder deadline(Date d) {
        deadline = d;
        return this;
    }

    public TaskBuilder timeframe(Date t) {
        timeframe = t;
        return this;
    }

    /**
     * Builds the correct task.
     * @return a Task of the correct class.
     */
    public Task build() {
        switch (type) {
        case TODO:
            return new ToDo(description);
        case EVENT:
            return new Event(description, timeframe);
        case DEADLINE:
            return new Deadline(description, deadline);
        default:
            return null;
        }
    }

}