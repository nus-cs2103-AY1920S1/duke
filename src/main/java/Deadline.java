import java.util.Date;

public class Deadline extends Task {
    private DateTime byWhen;

    public Deadline(String name, String byWhen) throws EmptyTaskDukeException, InvalidTaskDukeException  {
        super(name);
        if (name == null) {
            throw new EmptyTaskDukeException("deadline");
        }
        if (byWhen == null) {
            throw new InvalidTaskDukeException("deadline");
        }
        this.byWhen = DateTime.create(byWhen);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[D]");
        stringBuilder.append(super.toString());
        stringBuilder.append(" (");
        stringBuilder.append(byWhen);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
