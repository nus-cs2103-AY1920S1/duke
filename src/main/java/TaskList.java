import javax.print.ServiceUI;
import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    public ArrayList<Task> tasks = new ArrayList<>();

    public void listTasks(){
        int size = this.tasks.size();
        StringBuilder listOfTask = new StringBuilder();
        for (int i = 0; i < size; i++) {
            listOfTask.append(i + 1 + ". " + this.tasks.get(i) + "\n" + "     ");
        }
        Duke.print(listOfTask.toString());
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

}
