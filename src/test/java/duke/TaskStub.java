package duke;

import duke.task.Task;

class TaskStub extends Task {
    TaskStub() {
        super("");
    }

    @Override
    public String toString() {
        return done ? "done task" : "undone task";
    }

    @Override
    public String export() {
        return done ? "done export" : "undone export";
    }
}
