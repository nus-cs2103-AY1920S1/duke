public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {

    }

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public Task(String description, Boolean done) {
        this.description = description.trim();
        this.isDone = done;
    }

    public Task finish() {
        return new Task(this.description, true);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /*
    public static Task toTask(String s) {
        switch (s.charAt(1)) {
        case 'T': return new Todo(s.substring(7));
        case 'E': return new Event(s.substring(7, s.lastIndexOf('(') - 1),
                s.substring(s.lastIndexOf('(' ) + 5, s.length() - 1));
        case 'D': return new Deadline(s.substring(7, s.lastIndexOf('(') - 1),
                s.substring(s.lastIndexOf('(' ) + 5, s.length() - 1));
        default: return new Task(s.substring(7));
        }
    }
     */

    public static String markStringDone(String taskString) {
        return taskString.replace("\u2718", "\u2713");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + "\n";
    }

    //...
}

