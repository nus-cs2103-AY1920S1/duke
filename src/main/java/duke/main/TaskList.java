import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addToList(Task task) {
        taskList.add(task);
    }

    public void deleteFromList(int entry) throws DukeException {
        try {
            taskList.remove(entry - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but this task does not exist.");
        }
    }

    public void markAsDone(int entry) throws DukeException {
        try {
            taskList.get(entry - 1).setDone();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but this task does not exist.");
        }
    }

    public Task getTask(int i) {
        return taskList.get(i - 1);
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < taskList.size(); i++) {
            temp = temp + (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return temp;
    }
}
