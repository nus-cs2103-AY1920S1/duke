import java.util.List;
import java.util.ArrayList;

class BasicTaskModel implements TaskModelInterface {
    private List<TaskObserver> observers;

    public BasicTaskModel() {
        this.observers = new ArrayList<>();
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
        return 0;
    }
        



}
