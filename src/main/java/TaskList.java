import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    private OutputUtilities outputUtilities;

    public TaskList(LocalStorage storage) {
        this.taskList = new ArrayList<>();
        outputUtilities = new OutputUtilities(this, storage);
    }

    public TaskList(List taskList, OutputUtilities ou) {
        this.taskList = taskList;
        this.outputUtilities = ou;
    }



    public void addTask(Task t, boolean printMessage) throws DukeException {
        if (t.getTaskName().isBlank()) throw new EmptyTodoTextException("The description of a todo cannot be empty");
        taskList.add(t);
        if (printMessage) outputUtilities.printAddTaskMessage(t);
    }


    public void printTasks() {
       outputUtilities.printTasks();
    }

    public void markTaskAsCompleted(int taskNumber, boolean printMessage) throws DukeException {
        if (taskNumber < 1 || taskNumber > taskList.size()) throw new TaskDoesNotExistException("Task not found");

        Task t = taskList.get(taskNumber - 1);
        t.markAsCompleted();
        if (printMessage) outputUtilities.printMarkTaskAsCompletedMessage(t);
    }

    public void deleteTask(int taskNumber, boolean printMessage) {
        Task t = taskList.get(taskNumber - 1);
        taskList.remove(t);
        if (printMessage) outputUtilities.printDeleteTaskMessage(t);
    }

    public List<Task> getList() {
        return taskList;
    }





}
