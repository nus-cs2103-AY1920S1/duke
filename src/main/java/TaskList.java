import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds new task (without date) into list
     * @param task - task to add
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Marks task at given index as done
     * @param idx - Index of task in list
     * @throws IndexOutOfBoundsException - Throws error if given index is out of list bounds
     */
    public void done(int idx) throws IndexOutOfBoundsException {
        this.list.get(idx).markDone();
    }

    /**
     * Marks task at given index as done
     * @param idx - Index of task in list
     * @throws IndexOutOfBoundsException - Throws error if given index is out of list bounds
     */
    public void delete(int idx) throws IndexOutOfBoundsException {
        Task task = this.list.get(idx);
        this.list.remove(idx);
        UI.printSuccessDeleteTaskMessage(task, this.size());
    }

    /**
     * Returns task at index idx
     * @param idx - index of task
     * @return Task at index idx
     */
    public Task get(int idx) {
        return this.list.get(idx);
    }

    /**
     * Returns the current size of list
     * @return size of list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Prints out contents of list
     */
    public void print() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println((i+1) + "." + this.list.get(i));
        }
    }
}
