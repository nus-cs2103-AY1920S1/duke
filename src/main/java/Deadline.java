public class Deadline extends Task {
    private String byWhen;

    public String getByWhen() {
        return byWhen;
    }

    public Deadline(String name, String byWhen) throws EmptyTaskDukeException, InvalidTaskDukeException  {
        super(name);
        if (name == null) {
            throw new EmptyTaskDukeException("deadline");
        }
        if (byWhen == null) {
            throw new InvalidTaskDukeException("deadline");
        }
        // to add the colon
        String arr[] = byWhen.split(" ", 2);
        this.byWhen = byWhen;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[D]");
        stringBuilder.append(super.toString());
        stringBuilder.append(" (" + byWhen + ")");
        return stringBuilder.toString();
    }
}
