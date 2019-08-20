// @@author CS2103/T Software Engineering AY1920S1
// Referenced from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html
// with minor modifications 

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}