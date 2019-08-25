public class Event extends Task {
    private DateTime atTime;

    public Event(String name, String atTime) throws EmptyTaskDukeException, InvalidTaskDukeException {
        super(name);
        if (name == null) {
            throw new EmptyTaskDukeException("event");
        }
        if (atTime == null) {
            throw new InvalidTaskDukeException("event");
        }
        this.atTime = DateTime.create(atTime);
    }

    public DateTime getAtTime() {
        return atTime;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[E]");
        stringBuilder.append(super.toString());
        stringBuilder.append(" (");
        stringBuilder.append(atTime);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
