import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    protected Storage storage;

    public TaskList(Storage storage){
        this.taskList = new ArrayList<>();
        this.storage = storage;
    }

    public TaskList(ArrayList<Task> list, Storage storage){
        this.taskList = list;
        this.storage = storage;
    }

    public void addToList(Task task) throws DukeException {
        this.taskList.add(task);
        this.storage.addTask(task);
    }

    public ArrayList<Task> getList(){
        return this.taskList;
    }

    public Task getTaskByIndex(int index){
        return this.taskList.get(index - 1);
    }

    public void removeFromList(int index) throws DukeException {
        this.taskList.remove(index - 1);
        if (this.getSize() == index - 1){
            this.storage.refreshStorage(taskList);
            return;
        }
        reorderIndex(index);
        this.storage.refreshStorage(taskList);
    }

    public void setDoneInList(int index) throws DukeException {
        this.taskList.get(index - 1).setDone();
        this.storage.refreshStorage(taskList);
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
