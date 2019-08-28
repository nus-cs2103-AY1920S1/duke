import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public String getList() throws DukeException {
        int count = 1;
        StringBuilder output = new StringBuilder();

        output.append("Here are the tasks in your list:\n");

        // List and print all items stored
        for (Task item: this.taskList) {
            output.append(count++ + "." + item + '\n');
        }

        // Remove terminal newline character if at least 1 item inserted
        if (count > 1) {
            output.deleteCharAt(output.toString().length() - 1);
            return output.toString();
        } else {
            throw new DukeException("The task list is empty.");
        }
    }
}
