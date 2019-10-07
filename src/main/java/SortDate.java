import java.util.Comparator;

class SortDate implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t1.date().compareTo(t2.date());
    }
}