import java.util.ArrayList;
import java.util.List;

public class ChatLike {
    private String response;
    private List<Task> taskList = new ArrayList<Task>();

    public void add(String s) throws DukeException{
        String[] arrWords = s.split(" ");
        String tasksType = arrWords[0]; //Type of Task: ToDo, Event, Deadline
        String tasksDescr = ""; //Description of the task
        String tasksTime = ""; //Time the task is associated with (not for ToDo)

        for(int i = 1; i < arrWords.length; i++){
            if(arrWords[i].length() >= 1 && arrWords[i].charAt(0) == '/') {
                for(int k = i + 1; k < arrWords.length; k++) {
                    tasksTime += " " + arrWords[k]; //Stores time of the task
                }
                break;
            } else {
                tasksDescr += " " + arrWords[i];
            }
        }
        tasksDescr = tasksDescr.trim(); //Remove extra spaces
        tasksTime = tasksTime.trim();

        if (!(tasksType.equals("todo") || tasksType.equals("deadline") || tasksType.equals("event"))) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\n    ____________________________________________________________\n");
        }
        if (tasksDescr.equals("")) throw new DukeException(
                "    ____________________________________________________________\n     " +
                        "\u2639" +" OOPS!!! The description of a " + tasksType + " cannot be empty." +
                        "\n    ____________________________________________________________\n");
        if ((tasksType.equals("deadline") || tasksType.equals("event")) && tasksTime.equals(""))
            throw new DukeException(
                    "    ____________________________________________________________\n     " +
                            "\u2639" + " OOPS!!! The time of a " + tasksType + " cannot be empty." +
                            "\n    ____________________________________________________________\n");

        Task task;
        if (tasksType.equals("todo")) {
            task = new ToDo(tasksDescr);
            taskList.add(task);
        } else if (tasksType.equals("deadline")) {
            task = new Deadline(tasksDescr, tasksTime);
            taskList.add(task);
        } else if (tasksType.equals("event")) {
            task = new Event(tasksDescr, tasksTime);
            taskList.add(task);
        } else {
            return;
        }

        this.response = "Got it. I've added this task:\n       " + task
                + "\n     Now you have " + taskList.size() + " tasks in the list.";
        System.out.println(this);
    }

    public void byeUser() {
        this.response = "Bye. Hope to see you again soon!";
        System.out.println(this);
    }

    public void greet() { //Default starting for Duke
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(this);
    }

    public void list() { //Shows the list of events
        String taskListed = "Here are the tasks in your list:\n     ";
        for (int i = 0; i < this.taskList.size(); i++) {
            taskListed += (i + 1) + "." + taskList.get(i) ;
            if (i != this.taskList.size() - 1)
                taskListed += "\n     ";
        }
        this.response = taskListed;
        System.out.println(this);
    }

    /*
    Apart from the two types exceptions given, I came up with this possible exception
    In command done <taskNumber>, <taskNumber> should necessarily be less than the taskList size, else an exception is thrown,
    which I have handled here.
    Similar with delete <taskNumber>.
    I figured out that writing only "done" or only "delete" also throws an exception, but it can be rectified in
    later levels.
    */
    public void done(int n) throws DukeException{ //Marks a task to be completed by calling method of Task object
        if(n > this.taskList.size()) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! I'm sorry, but you do not have so many tasks :-(" +
                    "\n    ____________________________________________________________\n");
        }
        this.taskList.get(n - 1).mark();
        this.response = "Nice! I've marked this task as done:\n       " + this.taskList.get(n - 1);
        System.out.println(this);
    }

    public void delete(int n) throws DukeException{ //Marks a task to be completed by calling method of Task object
        if(n > this.taskList.size()) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! I'm sorry, but you do not have a task at that position to delete :-(" +
                    "\n    ____________________________________________________________\n");
        }
        Task taskToBeDel = this.taskList.get(n - 1);
        this.taskList.remove(taskToBeDel); //Deletes the task from taskList

        this.response = "Noted. I've removed this task:\n       " + taskToBeDel +
                "\n     Now you have " + taskList.size() + " tasks in the list."; //Shows truncated list
        System.out.println(this);
    }

    public String toString() {
        return "    ____________________________________________________________\n     "
                + this.response
                + "\n    ____________________________________________________________\n";
    }
}
