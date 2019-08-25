package Tasks;

public class Event extends Task{

    private String event_time;

    public Event(String task_name, String event_time) {
        super(task_name);
        this.event_time = event_time;
    }

    @Override
    public String task_info() {
        String indicator;
        if (isFinished()) indicator = "[\u2713] ";
        else indicator = "[\u2715] ";
        return "[E]" + indicator + get_name() + " (at: " + event_time + ")";
    }

    @Override
    public String record_info() {
        if (isFinished()) return "E|" + "1|" + get_name() + "|" + event_time;
        else return "E|" + "0|" + get_name() + "|" + event_time;
    }
}
