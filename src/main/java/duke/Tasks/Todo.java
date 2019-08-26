package duke.Tasks;

import duke.DukeException;

public class Todo extends Task {

    public Todo(String task_name) throws DukeException {
        super(task_name);
    }

    @Override
    public String task_info() {
        String indicator;
        if (isFinished()) indicator = "[\u2713] ";
        else indicator = "[\u2715] ";
        return "[T]" + indicator + get_name();
    }

    @Override
    public String record_info() {
        if (isFinished()) return "T|" + "1|" + get_name();
        else return "T|" + "0|" + get_name();
    }
}
