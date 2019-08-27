import java.util.StringJoiner;

public class Todo extends Task {

    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSaveString() {
        StringJoiner sj = new StringJoiner("|");
        sj.add("T");
        sj.add(isDone ? "1" : "0");
        sj.add(description);
        return sj.toString();
    }

    @Override
    public Todo parseSaveString(String saveString) {
        String[] saveStringArr = saveString.split("|");
        boolean isDone = saveStringArr[1].equals("1");
        String description = saveStringArr[2];
        Todo todo = new Todo(description);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }
}