import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }


    public void addToList(Task task) {
        this.taskList.add(task);
    }

    public ArrayList<Task> getList(){
        return this.taskList;
    }

    public Task getTaskAt(int index){
        return this.taskList.get(index - 1);
    }

    public void removeFromList(Task task) {
        this.taskList.remove(task);
    }

    public int getSize(){
        return this.taskList.size();
    }


}
