import java.util.ArrayList;
import java.util.List;

public class ChatLike {
    private String response;
    private List<Task> taskList = new ArrayList<Task>();

    public void add(String s) {
        this.response = "added: " + s;
        Task t = new Task(s);
        taskList.add(t);
        System.out.println(this);
    }

    public void byeUser() {
        this.response = "Bye. Hope to see you again soon!";
        System.out.println(this);
    }

    public void greet() {
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(this);
    }

    public void list() {
        String taskListed = "Here are the tasks in your list:\n     ";
        for (int i = 0; i < this.taskList.size(); i++) {
            taskListed += (i + 1) + "." + taskList.get(i) ;
            if (i != this.taskList.size() - 1)
                taskListed += "\n     ";
        }
        this.response = taskListed;
        System.out.println(this);
    }

    public void done(int n) {
        this.taskList.get(n - 1).mark();
        this.response = "Nice! I've marked this task as done:\n       " + this.taskList.get(n - 1);
        System.out.println(this);
    }

    public String toString() {
        return "    ____________________________________________________________\n     "
                + this.response
                + "\n    ____________________________________________________________\n";
    }
}

