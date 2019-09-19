import java.util.Comparator;

public class TodoSort implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        if (!(t1 instanceof Todo) && !(t2 instanceof Todo)) {
            return 0;
        } else if (!(t1 instanceof Todo)) {
            return 1;
        } else if (!(t2 instanceof Todo)) {
            return -1;
        }
        return t1.getDescription().compareTo(t2.getDescription());
    }
}
