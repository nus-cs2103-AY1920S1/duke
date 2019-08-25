import java.util.LinkedList;

public class TaskList {
    LinkedList<Task> tasks;
    public TaskList() {
        this.tasks = new LinkedList<Task>();
    }

    public TaskList(LinkedList<Task> lst) {
        this.tasks = lst;
    }

    public Task getTask(int id) {
        return this.tasks.get(id-1);
    }
    public LinkedList<Task> getAllTasks() {
        return this.tasks;
    }

    public void deleteTask(int i) throws DukeException {
        try {
            this.tasks.remove(i-1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! No such task exist!");
        }
    }

    public void completeTask(int i) throws DukeException {
        try {
            this.tasks.get(i-1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! No such task exist!");
        }
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public int getListSize() {
        return this.tasks.size();
    }

    public LinkedList<Task> findTasks(String keywords) {
        LinkedList<Task> results = new LinkedList<>();
        for (Task t : this.tasks) {
            if (t.toString().contains(keywords)) {
                results.add(t);
            }
        }
        return results;
    }
}
