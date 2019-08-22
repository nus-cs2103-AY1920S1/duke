import java.util.*;
public class Todo extends Task {
    String task;
    public Todo(String input) {
        super(input);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
