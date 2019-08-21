public class EventTask implements Task {
    public final String description;
    public final DukeDateTime startDateTime;
    public final DukeDateTime endDateTime;
    public final boolean isDone;

    //User inputs Date as DD/MM/YYYY and in Military Time
    public EventTask(String description, String startTimeString, String endTimeString) {
        this.description = description;

        String [] startTimeStrings = startTimeString.split(" ");
        startDateTime = new DukeDateTime(startTimeStrings[0], startTimeStrings[1]);

        String [] endTimeStrings = endTimeString.split(" ");
        endDateTime = new DukeDateTime(endTimeStrings[0], endTimeStrings[1]);

        this.isDone = false;
    }

    public EventTask(String description, DukeDateTime startDateTime, DukeDateTime endDateTime, boolean isDone) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isDone = isDone;
    }

    @Override
    public Task getTaskMarkedAsDone() {
        //Returns a copy of this task but with its completion status marked as done
        return new EventTask(description, startDateTime, endDateTime, true);
    }

    @Override
    public Task getTaskMarkedUndone() {
        //Returns a copy of this task but with its completion status marked as undone
        return new EventTask(description, startDateTime, endDateTime, false);
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
        sb.append(startDateTime.toString());
        sb.append(" to ");
        sb.append(endDateTime.toString());
        sb.append(")");
        return sb.toString();
    }
}
