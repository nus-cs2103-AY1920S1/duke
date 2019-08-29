package duke.bin;

import duke.bin.common.DukeException;
import duke.bin.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> textStorage = new ArrayList<>();

    public void store(Task task) {
        textStorage.add(task);
    }

    public void store(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            textStorage.add(task);
        }
    }

    public int getSize() {
        return textStorage.size();
    }

    public Task markAsDone(int index) throws DukeException {
        --index;
        if (index < 0 | index >= textStorage.size())
            throw new DukeException("There's no Task attached to that number");
        return textStorage.get(index).completed();
    }

    public Task delete(int index) throws DukeException {
        --index;
        if (index < 0 | index >= textStorage.size())
            throw new DukeException("There's no Task attached to that number");
        return textStorage.remove(index);
    }

    public ArrayList<Task> getList() {
        return textStorage;
    }
}
