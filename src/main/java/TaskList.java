import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void list() throws DukeException{
        if (list.size() == 1) {
            throw new DukeException("OOPS!!! You have no tasks to be displayed.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < list.size(); i++) {
                System.out.println(i + "." + list.get(i));
            }
        }
    }

    public void markAsDone(int num) throws DukeException {
        if (num >= list.size() || num < 1) {
            throw new DukeException("This task does not exist.");
        } else {
            System.out.println(list.get(num).done());
        }
    }

    public void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:\n" + list.get(list.size() - 1));
        System.out.println("Now you have " + (list.size() - 1) + " tasks in the list");
    }

    public void deleteTask(int num) throws DukeException {
        if (num >= list.size() || num < 1) {
            throw new DukeException("This task does not exist.");
        } else {
            System.out.println("Noted. I've removed this task:\n" + list.remove(num));
            System.out.println("Now you have " + (list.size() - 1) + " tasks in the list.");
        }
    }
}
