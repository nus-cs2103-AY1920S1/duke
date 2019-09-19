import java.util.Comparator;

public class EventSort implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        if (!(t1 instanceof Event) && !(t2 instanceof Event)) {
            return 0;
        } else if (!(t1 instanceof Event)) {
            return 1;
        } else if (!(t2 instanceof Event)) {
            return -1;
        }
        return ((Event) t1).getAt().compareTo(((Event) t2).getAt());
    }
}