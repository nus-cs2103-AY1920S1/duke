import java.util.ArrayList;
import java.util.Comparator;

class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    TaskList() {
    }

    Task get(int i) throws ListItemEmptyException {
        if (i > size() - 1) {
            throw new ListItemEmptyException();
        }
        return list.get(i);
    }

    void add(Task task) {
        list.add(task);
        list.sort((Task t1, Task t2) -> (t1.getDate() == null || t2.getDate() == null)
                ? 0
                : t1.getDate().compareTo(t2.getDate()));
    }

    Task remove(int i) throws ListItemEmptyException {
        if (i > size() - 1) {
            throw new ListItemEmptyException();
        }
        return list.remove(i);
    }

    int size() {
        return list.size();
    }

    String printFormat() {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Task item : list) {
            sb.append(++counter).append(".").append(item.getStatus()).append("\n");
        }
        return sb.toString();
    }

    String find(String s) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Task item : list) {
            if (item.description.contains(s)) {
                sb.append(++counter).append(".").append(item.getStatus()).append("\n");
            }
        }
        return sb.toString();
    }

    String saveFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task item : list) {
            sb.append(item.saveFormat()).append("\n");
        }
        return sb.toString();
    }
}
