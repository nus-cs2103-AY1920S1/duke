package tasklist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.TextUi;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents an entire tasklist and contains methods to edit and display the list.
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    private TextUi ui;

    public TaskList() {
        tasks = new ArrayList<Task>();
        ui = new TextUi();
    }


    public TaskList(ArrayList<Task> loadedList) {
        tasks = loadedList;
        ui = new TextUi();
    }

    /**
     * prints the ui after adding a new task.
     */
    public void printNewTask() {
        ui.printAddedItem(tasks.get(tasks.size() - 1).getOverallStatus(), tasks.size());
    }

    /**
     * prints the ui for listing tasks.
     */
    public void listTasks() {
        ui.printItemList(tasks);
    }

    /**
     * Changes the completion status of the task.
     * @param completedtask tells the method which task has been completed
     */
    public void completeTask(String completedtask) {
        int taskNumber = Integer.parseInt(completedtask);
        tasks.get(taskNumber - 1).completeTask();
        ui.printCompletedTask(tasks.get(taskNumber - 1).getOverallStatus());
    }

    /**
     * Method to add a task to the tasklist according to the correct type.
     * @param taskType determines type of task to add
     * @param description description of the task
     * @param completionStatus sets the completion status of the task
     * @param date sets the date of the task
     */
    public void addTask(String taskType, String description, boolean completionStatus, LocalDateTime date) {
        assert !description.isEmpty() : "description should not be empty";
        switch (taskType) {
        case "todo":
            tasks.add(new Todo(description,completionStatus,date));
            break;
        case "deadline":
            tasks.add(new Deadline(description, completionStatus, date));
            break;
        case "event":
            tasks.add(new Event(description, completionStatus, date));
            break;
        case "notebook":
            tasks.add(new Notebook(description,completionStatus,date));
            break;
        default:
            // not necessary as tasktype can only be the above 3
        }
    }

    /**
     * Deletes the task as specified by user.
     * @param deletedEvent determines the task to be deleted
     */
    public void removeTask(String deletedEvent) {
        if (deletedEvent.contains("all")) {
            tasks.clear();
            ui.printRemovedItem("All tasks", tasks.size() + 1);
        } else {
            int taskTodDelete = Integer.parseInt(deletedEvent);
            ui.printRemovedItem(tasks.get(taskTodDelete - 1).getOverallStatus(), tasks.size());
            tasks.remove(taskTodDelete - 1);
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches through whole tasklist to try and find a match with the user input.
     * @param search the string to search for
     */
    public void findTasks(String search) {
        ObservableList<String> foundtasks = FXCollections.observableArrayList();
        for (Task task: tasks) {
            if (task.getOverallStatus().contains(search)) {
                foundtasks.add(task.getOverallStatus());
            }
        }
        ui.printFoundTasks(foundtasks);
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


}
