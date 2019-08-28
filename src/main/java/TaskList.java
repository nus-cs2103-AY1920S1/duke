import java.util.ArrayList;

public class TaskList {
    public int numTasks = 0;
    private ArrayList<Task> allTasks;

    public TaskList() {
        allTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
        this.numTasks = allTasks.size();
    }

    public void addTask(Task newTask) {
        numTasks++;
        allTasks.add(newTask);
        //System.out.println(tasks.get(numTasks-1));
    }

    public void printAllTasks() {
        for (int i = 1; i <= numTasks; i++) {
            System.out.println(i + "." + allTasks.get(i-1));
        }
    }

    public void markAsDone(int index) {
        Task completedTask = allTasks.get(index - 1);
        if (!completedTask.isDone()) {
            completedTask.markAsDone();
        }
    }

    /**
     * Deletes the Task of the give index from the TaskList
     * @param index
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        Task deletedTask = allTasks.get(index);
        allTasks.remove(index);
        numTasks--;
        return deletedTask;
    }

    @Override
    public String toString() {
        String output = "";
        for (Task task : allTasks) {
            output = output + task + System.lineSeparator();
        }
        return output;
    }
}