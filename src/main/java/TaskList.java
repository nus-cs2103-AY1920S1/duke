import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;
    public TaskList(Storage storage) throws Exception {
        listOfTasks = storage.getList();
    }

    public TaskList() {
        listOfTasks = new ArrayList<Task>();
    }

    public boolean isEmpty() {
        if (listOfTasks.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return listOfTasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.listOfTasks;
    }
    public Task get(int index) {
        return listOfTasks.get(index);
    }

    public void removeTask(Task task) {
        listOfTasks.remove(task);
    }

    public void addTodo(Todo task) {
        listOfTasks.add(task);
    }

    public void addEvent(Event task) {
        listOfTasks.add(task);
    }

    public void addDeadline(Deadline task) {
        listOfTasks.add(task);
    }
}
