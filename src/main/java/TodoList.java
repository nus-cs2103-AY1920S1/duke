import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Task> tasks = new ArrayList<>();


    public void addTask(String taskName) {
        int id = tasks.size() + 1;
        tasks.add(new Task(id, taskName));
        OutputHandler.printLine();
        System.out.println("added: " + taskName);
        OutputHandler.printLine();
    }

    public void printTasks() {
        OutputHandler.printLine();
        tasks.forEach(System.out::println);
        OutputHandler.printLine();
    }


}
