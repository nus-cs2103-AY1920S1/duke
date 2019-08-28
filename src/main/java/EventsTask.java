public class EventsTask extends Task {
    String taskName;
    String taskDesc;

    public EventsTask(String task) {
        super(task);
        String[] taskSplit = task.split("/at");
        if(taskSplit.length < 2) {
            throw new EmptyDescriptionDukeException("event", "/at");
        }
        taskName = taskSplit[0].trim();
        taskDesc = taskSplit[1];
    }

    @Override
    public String toString() {
        String output = "[E]";
        if (super.completed) {
            output += "[✓]";
        } else {
            output += "[✗]";
        }
        output += " " + this.taskName + " (At: " + this.taskDesc + ")";
        return output;
    }
}