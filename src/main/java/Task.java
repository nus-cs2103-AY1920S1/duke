public class Task {
    protected String description;
    protected boolean isDone;
    protected static String[] checklist = new String[100];

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns tick or cross symbol.
    public String getStatusIcon() {
        //System.out.println("\u2713");
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;

    }
}
