import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class BasicTaskModel implements TaskModelInterface {
    private List<TaskObserver> observers;
    private List<TaskInterface> taskList;

    public BasicTaskModel() {
        this.observers = new ArrayList<>();
        this.taskList = new ArrayList<>();
    }

    public BasicTaskModel(Stream<TaskInterface> taskStream) {
        this.observers = new ArrayList<>();
        this.taskList = taskStream.collect(Collectors.toList());
    }

    public void loadData(Stream<TaskInterface> taskStream) {
        taskStream.forEach(x -> this.taskList.add(x));
        this.notifyObservers();
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

    public int getTotalTasks() {
        /* TODO arraylist.size()*/
        return this.taskList.size();
    }

    public void addTask(TaskInterface task) {
        this.taskList.add(task);
        this.notifyObservers();
    }

    // returns TaskInterface so task details can be printed
    // by the Display GUI
    public TaskInterface doneTask(int refNum) {
        //the datastructure list is index0 
        //the GUI list is index1
        int indexNum = refNum - 1;
        TaskInterface pendingTask = this.taskList.get(indexNum);
        TaskInterface doneTask = pendingTask.completeTask();
        this.taskList.set(indexNum, doneTask);
        this.notifyObservers();
        return doneTask;
    }

    public TaskInterface deleteTask(int refNum) {
        int indexNum = refNum - 1;
        TaskInterface deletedTask = 
            this.taskList.remove(indexNum);
        this.notifyObservers();
        return deletedTask;
    }

    public Iterator<TaskInterface> getTaskListIterator(){
        return this.taskList.listIterator();
    }

    public Stream<TaskInterface> getTaskStream() {
        return this.taskList.stream();
    }

    /* when do we notify the task again? */
    public void notifyObservers() {
        //System.out.println("Notifying... ");
        for (TaskObserver observer : observers) {
            observer.update(this);
        }    
    }
        



}
