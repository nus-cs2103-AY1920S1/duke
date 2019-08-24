public class Deadline extends Task{
    protected String timeDesc;

    public Deadline(String desc, String timeDesc) {
        super(desc);
        this.timeDesc = timeDesc;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + timeDesc + ")";
    }

    public String toFileFormat() {
        if(isDone) {
            String format = "D | [✓] | " + taskDesc + " | " + timeDesc + "\n";
            return format;
        } else {
            String format = "D | [✗] | " + taskDesc + " | " + timeDesc + "\n";
            return format;
        }
    }
}
