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


    public void addTask(Task t, boolean printMessage) throws EmptyTodoTextException {
        if (t.getTaskName().isBlank()) throw new EmptyTodoTextException("The description of a todo cannot be empty");
        taskList.add(t);
        if (printMessage) ui.printAddTaskMessage(t);
    }

    public void printTasks() {
       ui.printTasks();
    }

    public void markTaskAsCompleted(int taskNumber, boolean printMessage) throws TaskDoesNotExistException {
        if (taskNumber < 1 || taskNumber > taskList.size()) throw new TaskDoesNotExistException("Task not found");

        Task t = taskList.get(taskNumber - 1);
        t.markAsCompleted();
        if (printMessage) ui.printMarkTaskAsCompletedMessage(t);
    }

    public void deleteTask(int taskNumber, boolean printMessage) throws TaskDoesNotExistException {
        if (taskNumber > taskList.size()) throw new TaskDoesNotExistException("Task not found");

        Task t = taskList.get(taskNumber - 1);
        taskList.remove(t);
        if (printMessage) ui.printDeleteTaskMessage(t);
    }

    public List<Task> getList() {
        return taskList;
    }


}
