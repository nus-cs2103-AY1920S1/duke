import java.util.Comparator;

public class SortByCompleted implements Comparator<Task> {
    public int compare(Task a, Task b) {
        return -(a.getStatusIcon().compareTo(b.getStatusIcon()));
    }
}
