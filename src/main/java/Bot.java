import java.util.ArrayList;
import java.util.List;

public class Bot {
    private String response;
    private List<Task> tasks = new ArrayList<Task>();

    public void add(String s) throws DukeException{
        String[] arr = s.split(" ");
        String taskType = arr[0];
        String taskDes = "";
        String taskTime = "";

        for(int i = 1; i < arr.length; i++){
            if(arr[i].length() >= 1 && arr[i].charAt(0) == '/') {
                for(int j = i + 1; j < arr.length; j++) {
                    taskTime += " " + arr[j];
                }
                break;
            } else {
                taskDes += " " + arr[i];
            }
        }
        taskDes = taskDes.trim();
        taskTime = taskTime.trim();
        if (!(taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event"))) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\n    ____________________________________________________________\n");
        }
        if (taskDes.equals("")) throw new DukeException(
                "    ____________________________________________________________\n     " +
                "\u2639" +" OOPS!!! The description of a " + taskType + " cannot be empty." +
                "\n    ____________________________________________________________\n");
        if ((taskType.equals("deadline") || taskType.equals("event")) && taskTime.equals(""))
            throw new DukeException(
                "    ____________________________________________________________\n     " +
                "\u2639" + " OOPS!!! The time of a " + taskType + " cannot be empty." +
                "\n    ____________________________________________________________\n");

        Task task;
        if (taskType.equals("todo")) {
            task = new ToDo(taskDes);
            tasks.add(task);
        } else if (taskType.equals("deadline")) {
            task = new DeadLine(taskDes, taskTime);
            tasks.add(task);
        } else if (taskType.equals("event")) {
            task = new Event(taskDes, taskTime);
            tasks.add(task);
        } else {return;};

        this.response = "Got it. I've added this task:\n       " + task
                + "\n     Now you have " + tasks.size() + " tasks in the list.";
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
        String taskList = "Here are the tasks in your list:\n     ";
        for (int i = 0; i < this.tasks.size(); i++) {
            taskList += (i + 1) + "." + tasks.get(i) ;
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
