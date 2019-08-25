public class DeadlineTask extends Task {
    private final DukeDateTime deadlineTime;

    public DeadlineTask(String description, DukeDateTime deadlineTime) {
        this.description = description;
        this.deadlineTime = deadlineTime;
        this.isDone = false;
    }

    public DeadlineTask(String description, DukeDateTime deadlineTime, boolean isDone) {
        this.description = description;
        this.deadlineTime = deadlineTime;
        this.isDone = isDone;
    }

    @Override
    //Returns a copy of this task but with its completion status marked as done
    public Task getTaskMarkedAsDone() {
        return new DeadlineTask(description, deadlineTime, true);
    }

    @Override
    //Returns a copy of this task but with its completion status marked as undone
    public Task getTaskMarkedUndone() {
        return new DeadlineTask(description, deadlineTime, false);
    }

    @Override
    //Returns a string representation of the Task, including its type, completion status, description and deadline
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(TO_STRING_FORMAT, 'D', this.getStatusIcon(), this.description));
        sb.append(String.format(" (by %s)", deadlineTime.toString()));
        return sb.toString();
    }
}
