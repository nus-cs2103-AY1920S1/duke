import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    //Adds a task to the end of the TaskList
    public void add(Task task) {
        tasks.add(task);
    }

    //Deletes the task at the specified index by decreasing the index of all subsequent entries by 1.
    public Task deleteAt(int index) {
        int realIndex = index - 1;
        Task deletedTask = tasks.get(realIndex);
        tasks.remove(realIndex);
        return deletedTask;
    }
    
    //Replaces the task at the specified index with a clone marked as done
    public Task markAsDone(int index) {
        int realIndex = index - 1;
        tasks.set(realIndex, tasks.get(realIndex).getTaskMarkedAsDone());
        return tasks.get(realIndex);
    }

    //Returns whether or not the TaskList is empty
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    //Returns the number of elements in the TaskList
    public int size() {
        return tasks.size();
    }

    @Override
    //Converts the TaskList into a human-readable String
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int iterator = 1;
        for (Task t : tasks) {
            //For each Task t, print out one line of "X.[<Status>] Description"
            sb.append(iterator++);
            sb.append(".");
            sb.append(t.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

    //converts the TaskList into a computer-readable string
    public String toSaveFileString() {
        StringBuilder sb = new StringBuilder();

        for(Task t : tasks) {
            sb.append(t.toSaveFileString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
