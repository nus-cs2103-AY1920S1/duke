package duke.util;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void delete(int deletedItemNo, Ui ui) throws DukeException {
        try {
            Task itemRemoved = taskList.remove(deletedItemNo - 1);
            int numOfTask = taskList.size();
            ui.showDeleted(itemRemoved, numOfTask);
        } catch (Exception e) {
            throw new DukeException("    No more tasks to delete!");
        }
    }

    public void add(Task task, Ui ui) {
        taskList.add(task);
        int numOfTask = taskList.size();
        ui.showAdded(task, numOfTask);
    }

    public void done(int itemNo, Ui ui) {
        Task task = taskList.get(itemNo - 1);
        task.markAsDone();
        ui.showDone(task);
    }

    public void writeTo(Storage storage) throws DukeException {
        storage.writeToFile(taskList);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("     Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            output.append("     ").append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        return output.toString();
    }
}
