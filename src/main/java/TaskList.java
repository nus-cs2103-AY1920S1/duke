import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    public int getSize() {
        return this.taskList.size();
    }
    protected List<Task> getTaskList() {
        return this.taskList;
    }

    public void listTasks(Ui ui) {
        String[] taskStrings = new String[this.getSize() + 1];
        taskStrings[0] = "Here are the tasks in your list:";
        int listIndex = 0;
        for (Task t : this.taskList) {
            listIndex++;
            //int id = t.getId();
            //ASSUMPTION: ok fine need to at least print new id, if i dont wanna change the init id
            taskStrings[listIndex] = String.format("%d.%s", listIndex, t.toString());
        }
        ui.dukeRespond(taskStrings);
    }

    public void addData(Task task) {
        this.taskList.add(task);
    }

    public Task markDone(int id) {
        Task task = this.taskList.get(id - 1);
        task.setCompleted();
        return task;
    }

    public Task deleteTask(int id) {
        Task task = this.taskList.remove(id - 1);
        return task;
    }

    public static Task createTask(TaskType taskType, String description) throws DukeException {
        Task t = null;
        switch (taskType) {
        case T:
            t = Todo.create(description);
            break;
        case D:
            t = Deadline.create(description);
            break;
        case E:
            t = Event.create(description);
            break;
        default:
            throw new InvalidKeywordException("");
        }
        return t;
    }
}
