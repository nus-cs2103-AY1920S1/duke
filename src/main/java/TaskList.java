import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Class which stores all the tasks 
 */
class TaskList implements TaskModelInterface {
    private List<TaskObserver> observers;
    private List<TaskInterface> taskList;

    public TaskList() {
        this.observers = new ArrayList<>();
        this.taskList = new ArrayList<>();
    }

    public TaskList(Stream<TaskInterface> taskStream) {
        this.observers = new ArrayList<>();
        this.taskList = taskStream.collect(Collectors.toList());
    }

    /**
     * returns void, takes in Stream of TaskInterfaces adds them to 
     * internal task list
     */
    public void loadData(Stream<TaskInterface> taskStream) {
        taskStream.forEach(x -> this.taskList.add(x));
        this.notifyObservers();
    }

    /**
     * Returns void, provides interface method to register observer classes
     * to be notified whenever TaskList changes
     */
    public void registerObserver(TaskObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(TaskObserver observer){
        /* TODO */
    }

    public void initialize(){
        /* TODO another arraylist to keep track of tasks */
    }

    /**
     * Returns total Tasks in the tasklist currently
     */
    public int getTotalTasks() {
        /* TODO arraylist.size()*/
        return this.taskList.size();
    }

    /**
     * Returns void, adds input TaskInterface task to tasklist
     * @param task implements TaskInterface, task to add to taskList
     */
    public void addTask(TaskInterface task) {
        this.taskList.add(task);
        this.notifyObservers();
    }

    // returns TaskInterface so task details can be printed
    // by the Display GUI
    /**
     * Returns TaskInterface, replaces TaskInterface at an index in TaskList with a,
     *  generates a completed version of the Task and replaces it in the same
     *  location in TaskList, returns the newly generated TaskInterface to be 
     *  printed into onto Ui
     * @param refNum which number in the tasklist to mark as completed
     * @return TaskInterface class Task which is marked as completed.
     */
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

    /**
     * Return TaskInterface, removes a TaskInterface at an index in TaskList
     *  this would cause other Tasks to be pushed forward to fill the gap
     * @param refNum number in tasklist to remove task
     * @return TaskInterface class Task which is marked as completed.
     */
    public TaskInterface deleteTask(int refNum) {
        int indexNum = refNum - 1;
        TaskInterface deletedTask = 
            this.taskList.remove(indexNum);
        this.notifyObservers();
        return deletedTask;
    }

    /**
     * deprecated to be removed
     */
    public Iterator<TaskInterface> getTaskListIterator() {
        return this.taskList.listIterator();
    }

    /**
     * Return Stream of TaskInterfaces of Tasks that are stored in tasklist
     * this ensures integrity of internally stored list
     * @return Stream of TaskInterface classes
     */
    public Stream<TaskInterface> getTaskStream() {
        return this.taskList.stream();
    }

    /* when do we notify the task again? */
    /**
     * Return void, notifies all registered Observer classes that tasklist has 
     * changed
     */
    public void notifyObservers() {
        //System.out.println("Notifying... ");
        for (TaskObserver observer : observers) {
            observer.update(this);
        }    
    }
        



}
