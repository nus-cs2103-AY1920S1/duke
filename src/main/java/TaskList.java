import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    void add(Task task) {
        this.tasks.add(task);
    }

    String delete(int index) throws DukeException {
        try {
            return this.tasks.remove(index).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    int size() {
        return this.tasks.size();
    }

    Task get(int index) {
        return this.tasks.get(index);
    }

    void markAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markedAsDone();
    }

    ArrayList<Task> getAllTasks() {
        return this.tasks;
    }
}