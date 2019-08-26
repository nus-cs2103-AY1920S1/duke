package duke.Tasks;

import duke.DukeException;

public class Todo extends Task {

    public Todo(String task_name) throws DukeException {
        super(task_name);
    }

    @Override
    public String taskInfo() {
        String indicator;
        if (isFinished()) indicator = "[\u2713] ";
        else indicator = "[\u2715] ";
        return "[T]" + indicator + getName();
    }

    @Override
    public String recordInfo() {
        if (isFinished()) return "T|" + "1|" + getName();
        else return "T|" + "0|" + getName();
    }
}
