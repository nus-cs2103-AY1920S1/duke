package tasks;

import tasks.Task;

public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    public String toString() {
        return "T|" + super.toString().trim();
    }
}
