import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasksList;

    public TaskList(){
        this.tasksList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasksList){
        this.tasksList = tasksList;
    }
    public void addTask(Task t){
        tasksList.add(t);
    }
    public void deleteTask(int index){
        tasksList.remove(index);
        Task.totalTasks--;
    }
    public Task get(int index){
        return tasksList.get(index);
    }

    public int size(){
        return tasksList.size();
    }
    @Override
    public String toString(){
        String toReturn = "";
        int size = tasksList.size();
        for (int i = 0; i < size; i++) {
            Task curr = tasksList.get(i);
            toReturn = toReturn + (i + 1) + "." + curr.toString() + "\n";
        }
        return toReturn.trim();
    }
}
