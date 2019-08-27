import java.util.LinkedList;

class TaskList extends LinkedList<Task> {
    static LinkedList<Task> taskList;
    TaskList() {
        taskList = new LinkedList<>();
    }

    TaskList(LinkedList<Task> tasks) {
        taskList = new LinkedList<>(tasks);
    }

    @Override
    public int size() {
        return taskList.size();
    }

    @Override
    public Task get(int n) {
        return taskList.get(n);
    }
}
