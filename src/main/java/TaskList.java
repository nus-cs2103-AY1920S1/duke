import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    TaskList() {}

    void deleteTask(Task task) {
        taskList.remove(task);
    }

    void addTask(Task task) {
        taskList.add(task);
    }

    int getListSize() {
        return taskList.size();
    }

    Task getTask(int index) {
        return taskList.get(index -1);
    }
}
