import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public Task deleteAt(int index) {
        int realIndex = index - 1;
        Task deletedTask = tasks.get(realIndex);
        tasks.remove(realIndex);
        return deletedTask;
    }
    
    public Task markAsDone(int index) {
        int realIndex = index - 1;
        tasks.set(index, tasks.get(index).getTaskMarkedAsDone());
        return tasks.get(index);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        //Convert the taskList into a readable String
        StringBuilder sb = new StringBuilder();

        int iterator = 1;
        for (Task t : tasks) {
            //For each Task t, print out one line of "X.[<Status>] Description"
            sb.append(iterator++);
            sb.append(".");
            sb.append(t.toString());
            sb.append('\n');
        }
        return sb.toString();
    }
}