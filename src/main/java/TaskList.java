import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public addToList(Task task){
        this.taskList.add(Task task);
    }

    public removeFromList(int index){
        this.taskList.remove(index - 1);
        reorderIndex();
    }

    public setDoneinList(int index){
        this.taskList.get(index - 1).toggleDone();
    }
}
