import java.util.ArrayList;

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

    String saveFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task item : list) {
            sb.append(item.saveFormat()).append("\n");
        }
        return sb.toString();
    }
}
