/**
 * TaskLists represent the task list, and is in charge of addition and deletion of tasks.
 */
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

    /**
     * Looks through tasks in the task list for tasks that contain the user entered keyword.
     * Puts such tasks in a HashMap.
     * @param searchDescription keyword user entered to find a task
     * @return HashMap with task number as key and corresponding task as value
     */
    public HashMap<Integer, Task> search(String searchDescription) {
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

    /**
     * Deletes a task from the TaskList.
     * @param taskNumber index of task to delete
     */
    public void delete(Integer taskNumber) {
        taskList.remove(taskNumber.intValue());
        for (Task task : taskList) {
            System.out.println(task);
        }
    }
}
