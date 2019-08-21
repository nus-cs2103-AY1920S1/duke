public class Deadline extends Task{
    public Deadline(String description, String by) {
        super(description);
        super.tag = "[D]";
        super.information = "(by: " + by + ")";

    }
}
