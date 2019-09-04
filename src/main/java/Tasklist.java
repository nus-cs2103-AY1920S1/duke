import java.util.ArrayList;


public class Tasklist {

    public ArrayList<Task> tasks;
    private UI ui;
    private Storage storage;
    private Parser parser;

    public Tasklist() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        this.tasks = tasks;
    }

    public void addTodo(Todo todo) {
        tasks.add(todo);
    }

    public void addEvent(Event event) {
        tasks.add(event);
    }

    public void addDeadline(Deadline deadline) {
        tasks.add(deadline);
    }

    public ArrayList<Task> returnTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}