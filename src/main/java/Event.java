public class Event extends Task {
    private String by;
    public Event(String info, String type, String by) {
        super(info,type);
        this.by = by;
    }
    @Override
    public String printTask() {
        return super.printTask() + " (at: " + by + ")";
    }

}
