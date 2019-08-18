public class EventTask implements Task {
    public final String description;
    public final String timingString;
    public final boolean isDone;

    public EventTask(String description, String timingString) {
        this.description = description;
        this.timingString = timingString;
        this.isDone = false;
    }

    public EventTask(String description, String timingString, boolean isDone) {
        this.description = description;
        this.timingString = timingString;
        this.isDone = isDone;
    }

    @Override
    public Task getTaskMarkedAsDone() {
        //Returns a copy of this task but with its completion status marked as done
        return new EventTask(description, timingString, true);
    }

    @Override
    public Task getTaskMarkedUndone() {
        //Returns a copy of this task but with its completion status marked as undone
        return new EventTask(description, timingString, false);
    }

    @Override
    //Returns a string representation of the Task, including its type, completion status, description and duration
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E][");
        sb.append(Task.getStatusIcon(isDone));
        sb.append("] ");
        sb.append(this.description);
        sb.append(" (at: ");
        sb.append(timingString);
        sb.append(")");
        return sb.toString();
    }
}
