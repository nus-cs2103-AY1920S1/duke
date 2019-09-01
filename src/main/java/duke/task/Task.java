package duke.task;

import java.util.Hashtable;
import duke.Date;

public abstract class Task {
    private String text;
    private boolean done;

    private static Hashtable<Boolean, String> icons = new Hashtable<>() {{
        put(true, "✓");
        put(false, "✗");
    }};

    public Task(String text) {
        this.text = text;
        this.done = false;
    }

    public Task(String text, boolean done) {
        this.text = text;
        this.done = done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public String getText() {
        return this.text;
    }

    public boolean isDone() {
        return this.done;
    }

    String getIcon() {
        return icons.get(this.done);
    }

    public Date getDate() {
        if (this instanceof Deadline || this instanceof Event) {
            return this.getDate();
        } else {
            return null;
        }
    }

    public abstract String getType();
}
