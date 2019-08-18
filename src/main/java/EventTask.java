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
        return new EventTask(description, timingString, true);
    }

    @Override
    public Task getTaskMarkedUndone() {
        return new EventTask(description, timingString, false);
    }

    @Override
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
