package duke;


import duke.exception.DukeException;
import duke.exception.EmptyTodoTextException;
import duke.exception.TaskDoesNotExistException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    private UI ui;

    public TaskList(Storage storage) {
        this.taskList = new ArrayList<>();
        this.ui = new UI(this, storage);
    }

    public TaskList(List taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public void addTask(Task task, boolean printMessage) throws EmptyTodoTextException {
        if (task.getTaskName().isBlank()) throw new EmptyTodoTextException("The description of a todo cannot be empty");
        taskList.add(task);
        if (printMessage) ui.printAddTaskMessage(task);
    }

    public void printTasks() {
       ui.printTasks();
    }

    public void markTaskAsCompleted(int taskNumber, boolean printMessage) throws TaskDoesNotExistException {
        if (taskNumber < 1 || taskNumber > taskList.size()) throw new TaskDoesNotExistException("Task not found");

        Task task = taskList.get(taskNumber - 1);
        task.markAsCompleted();
        if (printMessage) ui.printMarkTaskAsCompletedMessage(task);
    }

    public void deleteTask(int taskNumber, boolean printMessage) throws TaskDoesNotExistException {
        if (taskNumber > taskList.size()) throw new TaskDoesNotExistException("Task not found");

        Task task = taskList.get(taskNumber - 1);
        taskList.remove(task);
        if (printMessage) ui.printDeleteTaskMessage(task);
    }

    public List<Task> findMatchingTasks(String searchTerm) {
        List<Task> searchResults = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTaskName().contains(searchTerm)) {
                searchResults.add(task);
            }
        }
        return searchResults;
    }
    public List<Task> getList() {
        return taskList;
    }


}
