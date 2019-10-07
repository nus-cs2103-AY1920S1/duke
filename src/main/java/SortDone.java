import java.util.Comparator;

class SortDone implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t1.getStatusIcon().compareTo(t2.getStatusIcon());
    }
}
