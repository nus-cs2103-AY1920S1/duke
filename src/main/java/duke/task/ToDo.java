package duke.task;

import duke.task.Task;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getType() {
        return "T";
    }

}
