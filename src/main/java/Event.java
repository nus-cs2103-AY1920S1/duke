public class Event extends Task{
    protected String atDateTime;

    public Event(String description, String atDateTime) {
        super(description);
        this.atDateTime = atDateTime;
        this.taskType = TaskType.EVENT;
    }

    public String getAtDateTime() {
        return atDateTime;
    }

    @Override
    public String toSaveString() {
        String saveString = super.toSaveString() + "@@@" + this.getAtDateTime();
        return saveString;
    }
    
    @Override
    public String toString() {
        String str = "["
                + "E"
                + "]["
                + this.getStatusIcon()
                + "] "
                + this.getDescription()
                + " (at: "
                + this.getAtDateTime()
                + ")";
        return str;
    }
}