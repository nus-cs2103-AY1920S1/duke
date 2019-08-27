import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Sets task at specified index of To Do List as done and returns it (one based numbering).
     *
     * @param i Index
     * @return Task at specified index.
     */
    public String done(int i) {
        tasks.get(i - 1).setDone(true);
        return tasks.get(i - 1).toString();
    }

    /**
     * Delete index of To Do List. (one based numbering)
     *
     * @param i Index
     * @return Deleted task
     */
    public String delete(int i) {
        String taskMessage = tasks.get(i - 1).toString();
        tasks.remove(i - 1);
        return taskMessage;
    }

    /**
     * Appends task to To Do List. (one based numbering)
     *
     * @param td ToDo
     * @return message to print
     */
    public String addToDo(ToDo td) {
        tasks.add(td);
        return tasks.get(tasks.size() - 1).toString();
    }

    /**
     * Appends and returns deadline to To Do List. (one based numbering).
     *
     * @param dl deadline
     * @return message to print
     */
    public String addDeadline(Deadline dl) {
        tasks.add(dl);
        return tasks.get(tasks.size() - 1).toString();
    }

    /**
     * Appends and returns event to To Do List. (one based numbering)
     *
     * @param e Event
     * @return message to print
     */
    public String addEvent(Event e) {
        tasks.add(e);
        return tasks.get(tasks.size() - 1).toString();
    }

    public int getTasksSize() {
        return tasks.size();
    }

    public String getTasks() {
        StringJoiner result = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            result.add(String.format("%d.%s", i + 1, t));
        }

        return result.toString();
    }
}