public class DeadlineTask implements Task {
    public final String description;
    public final DukeDateTime doByDateTime;
    public final boolean isDone;

    public DeadlineTask(String description, String doByString) {
        this.description = description;
        
        String [] dateTimeStrings = doByString.split(" ");
        this.doByDateTime = new DukeDateTime(dateTimeStrings[0], dateTimeStrings[1]);

        this.isDone = false;
    }

    public DeadlineTask(String description, DukeDateTime doByDateTime, boolean isDone) {
        this.description = description;
        this.doByDateTime = doByDateTime;
        this.isDone = isDone;
    }

    @Override
    //Returns a copy of this task but with its completion status marked as done
    public Task getTaskMarkedAsDone() {
        return new DeadlineTask(description, doByDateTime, true);
    }

    @Override
    //Returns a copy of this task but with its completion status marked as undone
    public Task getTaskMarkedUndone() {
        return new DeadlineTask(description, doByDateTime, false);
    }

    @Override
    //Returns a string representation of the Task, including its type, completion status, description and deadline
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D][");
        sb.append(Task.getStatusIcon(isDone));
        sb.append("] ");
        sb.append(this.description);
        sb.append(" (by: ");
        sb.append(doByDateTime.toString());
        sb.append(")");
        return sb.toString();
    }
}
