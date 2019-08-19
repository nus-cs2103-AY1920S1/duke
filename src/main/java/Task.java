public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String format_description() {
        String item = description.split("/")[0];
        String tag = description.split("/")[1];
        String prep = tag.substring(0, tag.indexOf(" "));
        String time = tag.substring(tag.indexOf(" ") + 1);
        return item + "(" + prep + ": " + time + ")";
    }

    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
