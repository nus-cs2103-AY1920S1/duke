import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ChatLike {
    private String response;
    private List<Task> taskList = new ArrayList<Task>();

    public void getResponseDirect() {
        System.out.println(this);
    }

    public void readFromFile(String str) throws DukeException{
        String arr[] = str.split(" \\| ");
        String tasksType = arr[0];
        int taskInfo = Integer.parseInt(arr[1]);
        String taskDescr = arr[2];
        String tasksTime = "";

        if (tasksType.equals("T")) {
            tasksType = "todo";
        } else if (tasksType.equals("D")) {
            tasksType = "deadline";
            tasksTime = " /by " + arr[3];
        } else if (tasksType.equals("E")) {
            tasksType = "event";
            tasksTime = " /at " + arr[3];
        }

        try {
            this.add(tasksType + " " + taskDescr + tasksTime);
        } catch (DukeException exc) {
            System.out.println(exc.getMessage());
        }

        if (taskInfo == 1) {
            this.done(taskList.size());
        }
    }

    public String genInfoForFile() {
        //Convert details of Task object into a String which will be stored in the file
        String taskData = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String currTask;

            if (task.getTimeLabel().equals("")) {
                currTask = task.getLabel() + " | " + task.getInfo() + " | " + task.getDescription();
            } else {
                currTask = task.getLabel() + " | " + task.getInfo() + " | " + task.getDescription() + " | "
                        + task.getTimeLabel();
            }

            if (i != taskList.size() - 1) {
                taskData += currTask + System.lineSeparator();
            } else {
                taskData += currTask;
            }
        }

        return taskData;
    }

    private static String getTimeFormat(int n) {
        if (n >= 11 && n <= 13) {
            return n + "th";
        }
        switch (n % 10) {
        case 1:
            return n + "st of";
        case 2:
            return n + "nd of";
        case 3:
            return n + "rd of";
        default:
            return n + "th of";
        }
    }

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

        try {
            Date date = new SimpleDateFormat("d/MM/yyyy HHmm").parse(tasksTime);
            SimpleDateFormat fmtr = new SimpleDateFormat("dd MMMM yyyy, hh:mm a");
            tasksTime = fmtr.format(date);
            String arr[] = tasksTime.split(" ");
            arr[0] = getTimeFormat(Integer.valueOf(arr[0]));
            arr[arr.length - 1] = arr[arr.length - 1].toLowerCase();
            tasksTime = "";
            for(int i = 0; i < arr.length; i++){
                tasksTime += " " + arr[i];
            }
            tasksTime = tasksTime.trim();
        } catch (ParseException e) {
            tasksTime = tasksTime;
        }

        Task task; //To be added in the taskList
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

    }

    public void greet() { //Default starting for Duke
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";

    }

    public void list() { //Shows the list of events
        String taskListed = "Here are the tasks in your list:\n     ";
        for (int i = 0; i < this.taskList.size(); i++) {
            taskListed += (i + 1) + "." + taskList.get(i) ;
            if (i != this.taskList.size() - 1)
                taskListed += "\n     ";
        }
        this.response = taskListed;

    }

    public void done(int n) throws DukeException{ //Marks a task to be completed by calling method of Task object
        if(n > this.taskList.size()) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! I'm sorry, but you do not have so many tasks :-(" +
                    "\n    ____________________________________________________________\n");
        }
        this.taskList.get(n - 1).mark();
        this.response = "Nice! I've marked this task as done:\n       " + this.taskList.get(n - 1);

    }

    public void delete(int n) throws DukeException{ //Deletes a task by calling method of Task object
        if(n > this.taskList.size()) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! I'm sorry, but you do not have a task at that position to delete :-(" +
                    "\n    ____________________________________________________________\n");
        }
        Task taskToBeDel = this.taskList.get(n - 1);
        this.taskList.remove(taskToBeDel); //Deletes the task from taskList

        this.response = "Noted. I've removed this task:\n       " + taskToBeDel +
                "\n     Now you have " + taskList.size() + " tasks in the list."; //Shows truncated list

    }

    public String toString() {
        return "    ____________________________________________________________\n     "
                + this.response
                + "\n    ____________________________________________________________\n";
    }
}
