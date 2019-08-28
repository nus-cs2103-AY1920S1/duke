import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;

public class Bot {
    private String response;
    private List<Task> tasks = new ArrayList<Task>();

    public void getResponse() {
        System.out.println(this);
    }

	private static String getOrdinal(int n) {
    if (n >= 11 && n <= 13) {
        return n + "th";
    }
    switch (n % 10) {
        case 1:  return n + "st of";
        case 2:  return n + "nd of";
        case 3:  return n + "rd of";
        default: return n + "th of";
    }
}

    public void add(String s) throws DukeException {
        String[] arr = s.split(" ");
        String taskType = arr[0];
        String taskDes = "";
        String taskTime = "";

        for (int i = 1; i < arr.length; i++) {//get task description
            if (arr[i].length() >= 1 && arr[i].charAt(0) == '/') {
                for (int j = i + 1; j < arr.length; j++) { //get task time
                    taskTime += " " + arr[j];
                }
                break;
            } else {
                taskDes += " " + arr[i];
            }
        }
        taskDes = taskDes.trim();
        taskTime = taskTime.trim();
        //handle exceptions
        if (!(taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event"))) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\n    ____________________________________________________________\n");
        }
        if (taskDes.equals("")) throw new DukeException(
                "    ____________________________________________________________\n     " +
                        "\u2639" + " OOPS!!! The description of a " + taskType + " cannot be empty." +
                        "\n    ____________________________________________________________\n");
        if ((taskType.equals("deadline") || taskType.equals("event")) && taskTime.equals(""))
            throw new DukeException(
                    "    ____________________________________________________________\n     " +
                            "\u2639" + " OOPS!!! The time of a " + taskType + " cannot be empty." +
                            "\n    ____________________________________________________________\n");					 
        try { 
	        Date date=new SimpleDateFormat("d/MM/yyyy HHmm").parse(taskTime);
	        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
	    taskTime = formatter.format(date);
	    String[] array = taskTime.split(" ");
	    array[0] = getOrdinal(Integer.valueOf(array[0]));
	    array[array.length - 1] = array[array.length - 1].toLowerCase();
	    taskTime = "";
	    for(int i = 0; i < array.length; i++){
	    taskTime += " " + array[i];
	    }
	    taskTime = taskTime.trim();
	    } catch (ParseException e) {
         taskTime = taskTime;
        }

        //creat task and add to tasks list
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
        } else {
            return;
        }

        //update response
        this.response = "Got it. I've added this task:\n       " + task
                + "\n     Now you have " + tasks.size() + " tasks in the list.";
    }

    public void exit() {
        //update response
        this.response = "Bye. Hope to see you again soon!";
    }

    public void greet() {
        //update response
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";
    }

    public void list() {
        //get the list of tasks in the arraylist
        String taskList = "Here are the tasks in your list:\n     ";
        for (int i = 0; i < this.tasks.size(); i++) {
            taskList += (i + 1) + "." + tasks.get(i);
            if (i != this.tasks.size() - 1) taskList += "\n     ";
        }
        this.response = taskList; //update response
    }

    public void done(int n) {
        Task task = this.tasks.get(n - 1);
        task.mark(); //mark the corresponding task as done;
        this.response = "Nice! I've marked this task as done:\n       " + task; //update response
    }

    public void delete(int n) {
        Task task = this.tasks.get(n - 1);
        this.tasks.remove(task); //delete task from list
        this.response = "Noted. I've removed this task: \n       " + task
                + "\n     Now you have " + tasks.size() + " tasks in the list."; //update response
    }

    public String toString() {
        return "    ____________________________________________________________\n     "
                + this.response
                + "\n    ____________________________________________________________\n";
    }
}
