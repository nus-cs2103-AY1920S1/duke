public class Event extends Task {
    private String atTime;

    public Event(String name, String atTime) throws EmptyTaskDukeException, InvalidTaskDukeException {
        super(name);
        if (name == null) {
            throw new EmptyTaskDukeException("event");
        }
        if (atTime == null) {
            throw new InvalidTaskDukeException("event");
        }
        this.atTime = atTime;
    }

    public String getAtTime() {
        return atTime;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[E]");
        stringBuilder.append(super.toString());
        stringBuilder.append(" (" + atTime + ")");
        return stringBuilder.toString();
    }
}
