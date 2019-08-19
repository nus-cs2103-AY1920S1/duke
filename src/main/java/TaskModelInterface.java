import java.util.Iterator;
interface TaskModelInterface {
    void initialize();
    void registerObserver(TaskObserver o);
    void removeObserver(TaskObserver o);
    int getTotalTasks();
    void addTask(TaskInterface task);
    TaskInterface doneTask(int refNum); 
    TaskInterface deleteTask(int refNum); 
    Iterator<TaskInterface> getTaskListIterator();
}
