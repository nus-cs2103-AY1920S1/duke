import java.util.ArrayList;

public class Ui {

    public void ui() {

    }

    public void greeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    public void goodbye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void taskAdded(ArrayList<Task> taskList){
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
    }

    public void taskRemoved(ArrayList<Task> taskList, int taskNumber){
        System.out.println("Noted. I've removed this task:\n" + taskList.get(taskNumber).toString());
        System.out.println("Now you have " + (taskList.size()-1) + " tasks in the list.\n");
    }

    public void taskDone(ArrayList<Task> taskList, int taskNumber){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(taskNumber).toString() + "\n");
    }


}
