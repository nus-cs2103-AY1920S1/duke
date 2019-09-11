package logic;

import task.Task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains the logic.TaskList and has the operations to add/delete tasks in the list.
 */
public class TaskList implements DukeList<Task> {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public List<Task> getList() {
        return taskList;
    }

    /**
     * Adds Tasks to Task List.
     *
     * @param task Task Obj to be added
     */
    @Override
    public void add(Task task) {
        assert task != null;
        taskList.add(task);
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("  " + task + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        Ui.loadStr(sb.toString());
    }

    /**
     * Find and filter tasks by keyword.
     *
     * @param taskDescription Keyword string to filter with
     * @return Filtered list of tasks
     */
    @Override
    public List<Task> find(String taskDescription) {
        return taskList.stream()
                .filter(task -> task.getDescription().contains(taskDescription))
                .collect(Collectors.toList());
    }

    /**
     * Task to be marked as done.
     *
     * @param taskNumStr String to be parsed as int
     * @throws DukeException If cannot parse string to int
     */
    public void markTask(String taskNumStr) throws DukeException {
        int taskNum = Parser.parseInt(taskNumStr, taskList);
        StringBuilder sb = new StringBuilder();
        Task task = taskList.get(taskNum - 1);
        assert task != null;
        task.markDone();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("  " + task.toString());
        Ui.loadStr(sb.toString());
    }

    /**
     * Task to be deleted from list of tasks.
     *
     * @param taskNumStr String to be parsed as int
     * @throws DukeException If cannot parse string to int
     */
    public void delete(String taskNumStr) throws DukeException {
        int taskNum = Parser.parseInt(taskNumStr, taskList);
        StringBuilder sb = new StringBuilder();
        Task task = taskList.get(taskNum - 1);
        assert task != null;
        sb.append("Noted. I've removed this task: \n");
        sb.append("  " + task + "\n");
        taskList.remove(taskNum - 1);
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        Ui.loadStr(sb.toString());
    }

}
