import java.util.Date;

class TaskBuilder {
    private String description;
    private Date deadline;
    private Date timeframe;
    private TaskType type;

    TaskBuilder type(TaskType t) {
        this.type = t;
        return this;
    }

    TaskBuilder description(String d) {
        description = d;
        return this;
    }

    TaskBuilder deadline(Date d) {
        deadline = d;
        return this;
    }

    TaskBuilder timeframe(Date t) {
        timeframe = t;
        return this;
    }

    Task build() {
        switch (type) {
            case TODO:
                return new ToDo(description);
            case EVENT:
                return new Event(description, timeframe);
            case DEADLINE:
                return new Deadline(description, deadline);
        }
        return null;
    }

}