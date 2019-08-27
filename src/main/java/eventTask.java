public class eventTask extends Task {

    String timeSlot;

    public eventTask(String taskInput, String timing) {
        super(taskInput);
        type = "E";
        timeSlot = timing;
    }

    @Override
    public String toString() {
        if(completed) {
            return "[" + type  + "]" + "[\u2713] " + name + "(at: " + timeSlot + ")";
        } else {
            return "[" + type + "]" + "[\u2718] " + name + "(at: " + timeSlot + ")";
        }
    }
}
