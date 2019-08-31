import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasksList){
        this.tasks = tasksList;
    }

    public TaskList(){ this.tasks = new ArrayList<>(); }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void deleteTask(int index){
        tasks.remove(index);
    }

    public Task getTask(int index){
        return tasks.get(index);
    }

    public int getSize(){
        return tasks.size();
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    @Override
    public String toString(){
        String toReturn = "";
        int length = tasks.size();
        for (int i = 0; i < length; i++) {
            int index = i + 1;
            toReturn = toReturn + index + "." + tasks.get(i) + "\n";
        }
        return toReturn;
    }
}
