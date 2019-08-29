import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * @return the taskList
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask (Task task, Storage storage) throws IOException {
        this.taskList.add(task);
        storage.addTaskToFile(task);
    }

    public ArrayList<Task> matchingTasks(String query) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : this.taskList) {
            String queryLowerCase = query.toLowerCase();
            String stringUnderCheck = t.toString().toLowerCase();
            if (stringUnderCheck.indexOf(queryLowerCase) != -1) {
                matches.add(t);
            }
        }
        return matches;
    }

    public void deleteTask(int index, Storage storage) throws DukeException, IOException {
        try {
            this.taskList.get(index - 1);
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The item specified does not exist.");
        }
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskList.get(index - 1).toString());
        this.taskList.remove(index - 1);
        storage.writeToFile(storage.getFilePath(), taskList);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void markAsDone(int index, Storage storage) throws DukeException, IOException {
        try {
            taskList.get(index - 1).markAsDone();
            storage.writeToFile(storage.getFilePath(), taskList);
        } 
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The item specified does not exist.");
        }
    }

}