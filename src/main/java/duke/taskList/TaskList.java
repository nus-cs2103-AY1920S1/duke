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

/**
 * A taskList storage an ArrayList of task of the user
 * @author Yang Shuting
 */
public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Construct a TaskList from existing list.
     * @param list  ArrayList of Task.
     * @throws DukeException thrown when the arrayList is empty.
     */
    public TaskList(ArrayList<Task> list) throws DukeException {
        if (list.size() > 0) {
            this.list = list;
        } else {
            throw new DukeException("Empty");
        }
    }

    /**
     *
     * @return a copy of the list
     */
    public ArrayList<Task> getList() {
        ArrayList<Task> copy = new ArrayList<>(list);
        return copy;
    }
}
