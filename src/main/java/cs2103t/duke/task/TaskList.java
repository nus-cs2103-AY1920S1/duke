package cs2103t.duke.task;

import cs2103t.duke.exception.DukeException;
import cs2103t.duke.exception.InvalidIDException;
import cs2103t.duke.exception.InvalidKeywordException;
import cs2103t.duke.ui.Ui;

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
    public List<Task> getTaskList() {
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

    /**
     * Adds given task into current list of tasks.
     * @param task the task to be added
     */
    public void addData(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks task at position id as done.
     * @param id the position at which the corresponding task in list is to be marked as done
     * @return the completed task
     * @throws DukeException
     */
    public Task markDone(int id) throws DukeException {
        if (id > getSize() || id < 1) {
            throw new InvalidIDException("" + id);
        }


        Task task = this.taskList.get(id - 1);
        task.setCompleted();
        return task;
    }

    /**
     * Deletes task at position id from list of tasks
     * Pre-condition: id is always valid.
     * @param id the position at which the corresponding task in list is to be deleted
     * @return the removed task
     * @throws DukeException
     */
    public Task deleteTask(int id) throws DukeException {
        if (id > getSize() || id < 1) {
            throw new InvalidIDException(""+id);
        }

        Task task = this.taskList.remove(id - 1);
        return task;
    }

    public static Task createTask(TaskType taskType, String description) throws DukeException {
        if (taskType == null) {
            throw new InvalidKeywordException("wrong keyword");
        }

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
            throw new InvalidKeywordException("wrong keyword");
        }
        return t;
    }

    public List<Task> findTasks(String wordToFind) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            //find in task.toString()
            if (task.toString().contains(wordToFind)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
