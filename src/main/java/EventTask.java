public class EventTask extends Task {

    public String time;

    public EventTask(String todo, String time) {
        super(todo);
        this.time = time;
    }

    public String toString() {
        if (completed) {
            return  String.format("[E}[✓] %s (at: %s)", this.todo, this.time);
        } else {
            return  String.format("[E}[✗] %s (at: %s)", this.todo, this.time);
        }
    }
}
