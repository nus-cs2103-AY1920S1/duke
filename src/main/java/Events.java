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
}
