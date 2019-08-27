/**
 * TaskLists represent the task list, and is in charge of addition and deletion of tasks.
 */

import java.util.ArrayList;

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
