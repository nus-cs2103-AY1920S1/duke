public class Event extends Task {
    String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (at: %s)", this.getMark(), this.getName(), this.getTime());
    }
}
