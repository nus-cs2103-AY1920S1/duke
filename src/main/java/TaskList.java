import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    public TaskList(ArrayList<Task> current) {
        this.tasks = current;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void add(Task current) {
        tasks.add(current);
    }

    public void delete(int taskNumber, DukeUI ui) throws IndexOutOfBoundsException{
        Task current = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        ui.printTaskDeletedMessage(current, tasks.size());
    }

    public void done (int taskNumber) throws DukeTaskDoneException, IndexOutOfBoundsException{
        Task current = tasks.get(taskNumber - 1);
        if(current.getStatus()) {
            throw new DukeTaskDoneException();
        } else {
            current.markAsDone();
        }
    }
}
