import java.util.Comparator;

class SortDescription implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t1.getDescription().compareTo(t2.getDescription());
    }
}
