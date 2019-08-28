public class eventTask extends Task {
    //Add variable for eventTask
    String timeSlot;

    public eventTask(String taskInput, boolean complete, String timing) {
        super(taskInput,complete);
        type = "E";
        timeSlot = timing;
    }

    public String getTime() {
        return timeSlot;
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
