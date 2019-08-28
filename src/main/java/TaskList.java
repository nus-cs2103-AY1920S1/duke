import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(tasks.size() > 0) {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1 + ". " + tasks.get(i) + "\n");
            }
        } else {
            sb.append("It looks like you have no tasks!");
        }
        return sb.toString().trim();
    }

    public void done(int i) {
        tasks.get(i).done();
    }

    public Task getTask(int i ) {
        return tasks.get(i);
    }

    public Task deleteTask(int i) throws DukeException {
        try {
            return tasks.remove(i);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("I don't think that task exists");
        }
    }

    public int getSize(){
        return tasks.size();
    }
}
