import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public void addToList(Task task){
        this.taskList.add(task);
    }

    public ArrayList<Task> getList(){
        return this.taskList;
    }

    public Task getTaskByIndex(int index){
        return this.taskList.get(index - 1);
    }

    public void removeFromList(int index){
        this.taskList.remove(index - 1);
        if (this.getSize() == index - 1){
            return;
        }
        reorderIndex(index);
    }

    public void setDoneInList(int index){
        this.taskList.get(index - 1).setDone();
    }

    public int getSize(){
        return this.taskList.size();
    }

    private void reorderIndex(int index){
        if (this.taskList.size() == 0){
            return;
        }
        for (int i = index - 1; i < this.getSize(); i++){
            taskList.get(i).id -= 1;
        }
    }
}
