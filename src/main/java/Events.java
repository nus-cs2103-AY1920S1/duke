public class Events extends Task{
    protected String timeDesc;

    public Events(String desc, String timeDesc) {
        super(desc);
        this.timeDesc = timeDesc;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + timeDesc + ")";
    }

    public String toFileFormat() {
        if(isDone) {
            String format = "E | [✓] | " + taskDesc + " | " + timeDesc + "\n";
            return format;
        } else {
            String format = "E | [✗] | " + taskDesc + " | " + timeDesc + "\n";
            return format;
        }
    }
}
