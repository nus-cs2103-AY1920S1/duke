import java.util.ArrayList;

/** Class to represent a list of tasks. */
class TaskList {
    // ArrayList to maintain list of tasks
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // add tasks
    public void addTask(Task task) {
        tasks.add(task);
    }

    // list tasks
    public String listTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("     %d. %s\n", i + 1, this.tasks.get(i).toString()));
        }
        return sb.toString();
    }

    // mark task as done
    public Task taskDone(int index) {
        this.tasks.get(index - 1).markDone();
        return this.tasks.get(index - 1);
    }

    // remove task from list
    public Task removeTask(int index) {
        return this.tasks.remove(index - 1);
    }

    // return tasks
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}