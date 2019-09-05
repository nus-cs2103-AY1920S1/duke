public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String deadline;
    protected String event;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getType() {
        if (type.equals("todo")) {
            return "T";
        } else if (type.equals("deadline")) {
            return "D";
        } else {
            return "E";
        }
    }

    public String getDesc() {
        return this.description;
    }

    /* mutators */

    public void isDone(boolean boo) {
        this.isDone = boo;
    }

    public void addType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "[\u2718] " + description;
    }
}