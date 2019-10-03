import java.util.Comparator;

public class DeadlineSort implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        if (!(t1 instanceof Deadline) && !(t2 instanceof Deadline)) {
            return 0;
        } else if (!(t1 instanceof Deadline)) {
            return 1;
        } else if (!(t2 instanceof Deadline)) {
            return -1;
        }
        return ((Deadline) t1).getBy().compareTo(((Deadline) t2).getBy());
    }
}