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

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    public void delete(int num) {
        tasks.remove(num);
    }

    public void listout() {
        for (int i = 0; i < tasks.size(); i ++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i));
        }
    }
}