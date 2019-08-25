import java.util.ArrayList;

import java.io.Serializable;

class TaskList implements Serializable {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task deleteTask(int index, Ui uiManager) throws DukeException {
        try {
            return this.taskList.remove(index);
        } catch (Exception e) {
            uiManager.throwMissingTaskError();
            return null;
        }
        
    }

    public int listSize() {
        return this.taskList.size();
    }

    public Task getTask(int index, Ui uiManager) throws DukeException {
        try {
            return this.taskList.get(index);
        } catch (Exception e) {
            uiManager.throwMissingTaskError();
        }
        return this.taskList.get(index);
    }

}