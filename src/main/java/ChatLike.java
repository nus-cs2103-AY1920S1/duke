import java.util.ArrayList;
import java.util.List;

public class ChatLike {
    private String response;
    private List<Task> taskList = new ArrayList<Task>();

    public void add(String s) {
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

    public void done(int n) { //Marks a task to be completed by calling method of Task object
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
