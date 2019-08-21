public class Event extends Task {

    String time;

    public Event(int num, String task, String time) {
        super(num, task);
        this.time = time;
    }

    @Override
    public String addTask() {
        return "     Got it. I've added this task: \n" + "     [E][✗] " + task + " (at: " + time + ")";
    }
}
