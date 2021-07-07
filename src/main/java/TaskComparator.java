import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    /**
     * Compares taskName of two tasks in lexicographical order
     *
     * @param taskA a Task object
     * @param taskB a Task object
     */
    public int compare(Task taskA, Task taskB) {
        return taskA.getTask().compareTo(taskB.getTask());
    }
}
