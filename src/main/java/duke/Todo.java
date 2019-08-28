package duke;
/*
 * To-do.java
 * CS2103T
 * @author Gabriel Ong
 *
 * This class represents a To-do Task.
 *
 */

import java.io.Serializable;

public class Todo extends Task implements Serializable {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
