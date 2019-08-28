import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    private int numTask;

    public TaskList() {
        this.taskList = new ArrayList<>();
        numTask = 0;
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        numTask = taskList.size();
    }

    public int getNumTask() {
        return numTask;
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    public Task getTask() {
        return taskList.get(numTask - 1);
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public Task doneTask(String s) {
        Task t = taskList.get(Integer.valueOf(s) - 1);
        t.markAsDone();
        return t;
    }

    public Task deleteTask(String s) {
        int listRank = Integer.valueOf(s) - 1;
        Task t = taskList.get(listRank);
        taskList.remove(listRank);
        numTask--;
        return t;
    }

    public void todoTask(String s) {
        taskList.add(new Todo(s));
        numTask++;
    }

    public void deadlineTask(String[] s) {
        taskList.add(new Deadline(s[0], s[1]));
        numTask++;
    }

    public void eventTask(String[] s) {
        taskList.add(new Event(s[0], s[1]));
        numTask++;
    }
}