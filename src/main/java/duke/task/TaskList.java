package duke.task;
import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> toDoList;

    public TaskList(ArrayList<Task> toDoList){
        this.toDoList = toDoList;
    }

    public TaskList(){
        this.toDoList = new ArrayList<>();
    }

    public int getSize(){
        return this.toDoList.size();
    }

    public void addTaskToList(Task taskToAdd){
        this.toDoList.add(taskToAdd);
    }

    public Task getTaskFromList(int taskNumToGet) throws DukeException {
        if (taskNumToGet <= 0 || taskNumToGet > this.getSize()) {
            throw new DukeException("Task Number is out of bounds");
        }
        return this.toDoList.get(taskNumToGet - 1);
    }

    public Task deleteTaskFromList(int taskNumToDelete) throws DukeException{
        if (taskNumToDelete <= 0 || taskNumToDelete > this.getSize()) {
            throw new DukeException("Task Number is out of bounds");
        }
        return this.toDoList.remove(taskNumToDelete - 1);
    }

    public Task markTaskAsDone(int taskNumToMark) throws DukeException{
        if (taskNumToMark <= 0 || taskNumToMark > this.getSize()) {
            throw new DukeException("Task Number is out of bounds");
        }
        Task taskToMark = this.getTaskFromList(taskNumToMark - 1);
        taskToMark.markAsDone();
        return taskToMark;
    }

    public void listAllTasks(Ui ui) {
        ui.messageUser("Here are the tasks in your list:");
        int startNumber = 1;
        ArrayList<String> allTasks = new ArrayList<>();
        for (Task t : this.toDoList) {
            allTasks.add("" + startNumber + "." + t.getTaskStatus());
            startNumber++;
        }
        ui.messageUser(allTasks);
    }

    public String listAllTasksAsString(){
        StringBuilder allTasks = new StringBuilder();
        for (Task task : this.toDoList) {
            allTasks.append(task.getStoredTaskStatus());
            allTasks.append(System.lineSeparator());
        }
        return allTasks.toString();
    }

}
