import java.util.ArrayList;
import java.util.HashMap;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Integer getNumberOfTasks() {
        return taskList.size();
    }
    public Task getTask(Integer index) {
        return taskList.get(index);
    }

    public HashMap<Integer, Task> search(String searchDescription) {
        System.out.println("in search method");
        HashMap<Integer, Task> searchResults = new HashMap<Integer, Task>();
        Integer index = 1;

        for (Task task : taskList) {
            String description = task.getDescription();
            if (description.contains(searchDescription)) {
                searchResults.put(index, task);
            }
            index++;
        }

        return searchResults;
    }

    public void add(Task userTask) {
        taskList.add(userTask);
    }

    public void delete(Integer taskNumber) {
        taskList.remove(taskNumber.intValue());
        for (Task task : taskList) {
            System.out.println(task);
        }
    }
}
