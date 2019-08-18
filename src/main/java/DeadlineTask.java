public class DeadlineTask implements Task {
    public final String description;
    public final String doByString;
    public final boolean isDone;

    public DeadlineTask(String description, String doByString) {
        this.description = description;
        this.doByString = doByString;
        this.isDone = false;
    }

    public DeadlineTask(String description, String doByString, boolean isDone) {
        this.description = description;
        this.doByString = doByString;
        this.isDone = isDone;
    }

    @Override
    public Task getTaskMarkedAsDone() {
        return new DeadlineTask(description, doByString, true);
    }

    @Override
    public Task getTaskMarkedUndone() {
        return new DeadlineTask(description, doByString, false);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D][");
        sb.append(Task.getStatusIcon(isDone));
        sb.append("] ");
        sb.append(this.description);
        sb.append("(by: ");
        sb.append(doByString);
        sb.append(")");
        return sb.toString();
    }
}
