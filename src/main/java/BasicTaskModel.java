import java.util.List;
import java.util.ArrayList;

class BasicTaskModel implements TaskModelInterface {
    private List<TaskObserver> observers;
    private List<TaskInterface> taskList;

    public BasicTaskModel() {
        this.observers = new ArrayList<>();
        this.taskList = new ArrayList<>();
    }

    public void registerObserver(TaskObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(TaskObserver observer){
        /* TODO */
    }

    public void initialize(){
        /* TODO another arraylist to keep track of tasks */
    }

    public int getTotalTasks(){
        /* TODO arraylist.size()*/
        return this.taskList.size();
    }

    public void addTask(TaskInterface task) {
        this.taskList.add(task);
        this.notifyObservers();
    }

    /* when do we notify the task again? */
    public void notifyObservers(){
        for (TaskObserver observer : observers) {
            observer.update(this);
        }    
    }
        



}
