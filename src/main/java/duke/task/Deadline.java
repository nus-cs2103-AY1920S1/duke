package duke.task;

public class Deadline extends Task{
    protected String  by;

    public Deadline(String description, boolean isDone, String info) {
        super(description, isDone, info);
        super.type = Type.DEADLINE;
        String[] infos = info.split(" ", 2);
        by =  super.checkDate(infos[1]);
    }

    @Override
    public String toString() {

        return "[D][" + getStatusIcon() + "] " + description + " (by: " +  by + ")";
    }

    @Override
    public String getFileStringFormat() {
        if (isDone()) {
            return "D | 1 | " + description + " | " + info;
        } else {
            return "D | 0 | " + description + " | " + info;
        }
    }
}
