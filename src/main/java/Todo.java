/*
 * To-do.java
 * Level-6
 * CS2103T
 * @author Gabriel Ong
 *
 * This class represents a To-do Task.
 *
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
