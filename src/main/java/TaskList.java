import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    //Lists out all the tasks in Duke
    public void list() throws IllegalArgumentException {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException("Nothing found in list");
        }
        int number = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            String outputString = number + ". " + task.toString();
            System.out.println(outputString);
            number++;
        }
    }

    public void add(Task task) {
        tasks.add(task);
        String outputString = "Got it. I've added this task: \n" + task.toString();
        System.out.println(outputString);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void done(int number) throws IndexOutOfBoundsException {
        if (number > tasks.size() || number <= 0) {
            throw new IndexOutOfBoundsException("The task number does not exist.");
        }
        Task task = tasks.get(number - 1);
        task.setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    public void delete(int number) throws IndexOutOfBoundsException {
        if (number > tasks.size() || number <= 0) {
            throw new IndexOutOfBoundsException("The task number does not exist.");
        }
        Task task = tasks.get(number - 1);
        tasks.remove(number - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

}
