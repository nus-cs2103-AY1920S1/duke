public class Event extends Task{
    protected String at;
    public Event(String description, String info) {
        super(description, info);
        super.type = Type.EVENT;
        String[] infos = info.split(" ", 2);
        this.at = infos[1] ;
    }

    @Override
    public String toString() {

        return "[E][" + getStatusIcon() + "] " + description + " (at: " + at + ")";
    }
}
