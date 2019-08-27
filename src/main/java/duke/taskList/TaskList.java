package duke.taskList;

import duke.Duke;
import duke.parser.Task;
import duke.storage.Storage;
import duke.DukeException;
import duke.parser.Todo;
import duke.parser.Event;
import duke.parser.Deadline;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) throws DukeException {
        if (list.size() > 0) {
            this.list = list;
        } else {
            throw new DukeException("Empty");
        }
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
