import java.util.Iterator;
import java.util.stream.Stream;
interface TaskModelInterface {
    void initialize();
    void registerObserver(TaskObserver o);
    void removeObserver(TaskObserver o);
    int getTotalTasks();
    void addTask(TaskInterface task);
    TaskInterface doneTask(int refNum); 
    TaskInterface deleteTask(int refNum); 
    //delete this
    Iterator<TaskInterface> getTaskListIterator();
    Stream<TaskInterface> getTaskStream();
    void loadData(Stream<TaskInterface> taskStream);
}
