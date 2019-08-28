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
    protected List<Task> getTaskList() {
        return this.taskList;
    }

    public void listTasks(Ui ui) {
        String[] taskStrings = new String[this.taskList.size() + 1];
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

    public void addData(Ui ui, String input) throws DukeException {
            //InvalidKeywordException, EmptyDescriptionException, IncorrectTaskFormatException {
        Parser ps = new Parser(input);
        Task newTask = createTask(ps.getInputTaskType(), ps.getInputEntireDescription());

        this.taskList.add(newTask);

        ui.dukeRespond("Got it. I've added this task:",
                "  " + newTask.toString(),
                String.format("Now you have %d tasks in the list", this.taskList.size()));
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

    public void markDone(Ui ui, String cmd) throws InvalidIDException, NoIDGivenException {
        String[] tmp = cmd.split("\\s+");

        if (tmp.length < 2)
            throw new NoIDGivenException("done");

        int id;
        try {
            id = Integer.parseInt(tmp[1]);
        } catch (NumberFormatException e) {
            throw new InvalidIDException(tmp[1]);
        }

        if (id > this.taskList.size() || id < 1) {
            throw new InvalidIDException(""+id);
        }

        Task task = this.taskList.get(id - 1);
        task.setCompleted();
        ui.dukeRespond("Nice! I've marked this task as done:",
                "  " + task.toString());
    }
    public void deleteTask(Ui ui, String cmd) {
        String[] tmp = cmd.split("\\s+");

        if (tmp.length < 2)
            throw new NoIDGivenException("delete");

        int id;
        try {
            id = Integer.parseInt(tmp[1]);
        } catch (NumberFormatException e) {
            throw new InvalidIDException(tmp[1]);
        }

        if (id > this.taskList.size() || id < 1) {
            throw new InvalidIDException(""+id);
        }

        Task task = this.taskList.remove(id - 1);
        ui.dukeRespond("Noted. I've removed this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", this.taskList.size()));
    }
}
