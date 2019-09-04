import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int numTask;

    public TaskList() {
        this.tasks = new ArrayList<>();
        numTask = 0;
    }

    public TaskList(ArrayList<Task> existingTasks) {
        this.tasks = existingTasks;
        numTask = existingTasks.size();
    }

    public int getNumTask() {
        return numTask;
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public Task getTask() {
        return tasks.get(numTask - 1);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public Task getDoneTask(String index) {
        Task t = tasks.get(Integer.valueOf(index) - 1);
        t.setDone();
        return t;
    }

    public Task deleteTask(String index) {
        int listRank = Integer.valueOf(index) - 1;
        Task t = tasks.get(listRank);
        tasks.remove(listRank);
        numTask--;
        return t;
    }

    public void addTodoTask(String detail) {
        tasks.add(new Todo(detail));
        numTask++;
    }

    public void addDeadlineTask(String[] details) {
        tasks.add(new Deadline(details[0], details[1]));
        numTask++;
    }

    public void addEventTask(String[] details) {
        tasks.add(new Event(details[0], details[1]));
        numTask++;
    }
}