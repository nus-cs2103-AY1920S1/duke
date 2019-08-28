import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    // Overloaded method for existing task lists
    public TaskList(List l) {
        taskList = new ArrayList<Task>(l);
    }


    public void loadData(List<Task> loadedTaskList) throws FileNotFoundException{
        taskList = loadedTaskList;
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) throws IndexOutOfBoundsException{
        return taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task removeTask(int index) throws IndexOutOfBoundsException{
        return taskList.remove(index);
    }

    public List<Task> getMatchingTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task k : taskList) {
            if (k.checkMatch(keyword))
                matchingTasks.add(k);
        }
        return matchingTasks;
    }

    public Task completeTask(int entryNumber) throws IndexOutOfBoundsException{
        Task taskToComplete = this.getTask(entryNumber - 1);
        taskToComplete.setDone();
        return taskToComplete;
    }

    public Task deleteTask(int entryNumber) throws IndexOutOfBoundsException{
        return this.removeTask(entryNumber - 1);
    }
}
