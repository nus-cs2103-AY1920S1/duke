public class EventTask extends Task {

    public String time;

    public EventTask(String todo, String time) {
        super(todo);
        this.time = time;
    }

    public EventTask(String todo, boolean isCompleted, String time) {
        super(todo, isCompleted);
        this.time = time;
    }

    public String toString() {
        if (isCompleted) {
            return  String.format("[E][Y] %s (at: %s)", this.todo, this.time);
        } else {
            return  String.format("[E][N] %s (at: %s)", this.todo, this.time);
        }
    }
}
