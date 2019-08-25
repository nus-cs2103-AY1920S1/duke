public class EventTask extends Task {
    private final DukeDuration eventDuration;

    public EventTask(String description, DukeDuration eventDuration) {
        this.description = description;
        this.eventDuration = eventDuration;
        this.isDone = false;
    }

    public EventTask(String description, DukeDuration eventDuration, boolean isDone) {
        this.description = description;
        this.eventDuration = eventDuration;
        this.isDone = isDone;
    }

    @Override
    //Returns a copy of this task but with its completion status marked as done
    public Task getTaskMarkedAsDone() {
        return new EventTask(description, eventDuration, true);
    }

    @Override
    //Returns a copy of this task but with its completion status marked as undone
    public Task getTaskMarkedUndone() {
        return new EventTask(description, eventDuration, false);
    }

    @Override
    //Returns a string representation of the Task, including its type, completion status, description and deadline
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(TO_STRING_FORMAT, 'E', this.getStatusIcon(), this.description));
        sb.append(String.format(" (at %s)", eventDuration.toString()));
        return sb.toString();
    }
}
