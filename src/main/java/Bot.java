import java.util.ArrayList;
import java.util.List;

public class Bot {
    private String response;
    private List<Task> tasks = new ArrayList<Task>();

    public void add(String s) {
        Task task = new Task(s);
        this.response = "added: " + s;
        tasks.add(task);
        System.out.println(this);
    }

    public void exit() {
        this.response = "Bye. Hope to see you again soon!";
        System.out.println(this);
    }

    public void greet() {
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(this);
    }

    public void list() {
        String taskList = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            taskList += (i + 1) + ". " + tasks.get(i) ;
            if (i != this.tasks.size() - 1) taskList += "\n     ";
        }
        this.response = taskList;
        System.out.println(this);
    }

    public void done(int n) {
        this.tasks.get(n - 1).mark();
        this.response = "Nice! I've marked this task as done:\n       " + this.tasks.get(n - 1);

        System.out.println(this);
    }

    public String toString() {
        return "    ____________________________________________________________\n     "
                + this.response
                + "\n    ____________________________________________________________\n";
    }
}
