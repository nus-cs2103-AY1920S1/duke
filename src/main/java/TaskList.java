import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> ls;

    public TaskList() {
        ls = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    public void add(Task t) {
        ls.add(t);
    }

    /**
     * Returns a serialised TaskList.
     *
     * @return Serialised TaskList as String
     */
    public String getSerialized() {
        StringBuilder sb = new StringBuilder();
        Iterator<Task> it = ls.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toFileString())
                .append((char) 30);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Returns String with all items to be displayed on Ui.
     *
     * @return String with all items to be displayed on Ui
     */
    public String toUiString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int i = 1;
        Iterator<Task> it = ls.iterator();
        while (it.hasNext()) {
            sb.append(i++)
                .append(".")
                .append(it.next());
        }
        return sb.toString();
    }

    public int size() {
        return ls.size();
    }

    /**
     * Remove the task from TaskList, then returns the task.
     *
     * @param index index as int
     * @return Task that is being removed from TaskList
     * @throws IndexOutOfBoundsDukeException On index out of bound
     */
    public Task remove(int index) throws IndexOutOfBoundsDukeException {
        try {
            return ls.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsDukeException();
        }
    }

    /**
     * Sets the task at index as done, then returns the task.
     *
     * @param index index as int
     * @return Task that is being set as done
     * @throws IndexOutOfBoundsDukeException On index out of bound
     */
    public Task setDone(int index) throws IndexOutOfBoundsDukeException {
        try {
            Task t = ls.get(index - 1);
            t.setDone();
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsDukeException();
        }
    }
}
