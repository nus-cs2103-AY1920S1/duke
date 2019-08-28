public class Event extends Task{
    protected String at;
    public Event(String description, boolean isDone,  String info) {
        super(description, isDone, info);
        super.type = Type.EVENT;
        String[] infos = info.split(" ", 2);
        this.at = infos[1] ;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + at + ")";
    }

    @Override
    public String getFileStringFormat() {
        if (isDone()) {
            return "E | 1 | " + description + " | " + info;
        } else {
            return "E | 0 | " + description + " | " + info;
        }
    }
}
