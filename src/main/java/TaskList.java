import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> myTaskList;

    public TaskList() {
        myTaskList = new ArrayList<>();
    }

    //Format for printing the "adding"
    private void spacerForTasks(Task inputTask) {
        String separator = "    ____________________________________________________________";
        String addingTask = "    Got it. I've added this task:";
        String converted = "        "+ inputTask;
        String taskTracking = "    Now you have " + myTaskList.size() + " tasks in the list.";
        System.out.println(separator);
        System.out.println(addingTask);
        System.out.println(converted);
        System.out.println(taskTracking);
        System.out.println(separator + "\n");
    }


    //Add and print the added notes
    public void addTasks(String addedTask) throws DukeException {
        Task temp;
        if(addedTask.contains("todo")&&(addedTask.length()>4)) {
            String replaced = addedTask.replace("todo ","");
            temp = new todoTask(replaced);
            myTaskList.add(temp);
            spacerForTasks(temp);
        } else if(addedTask.contains("deadline")&&(addedTask.length()>8)) {
            String replaced = addedTask.replace("deadline ","");
            String[] deadLines = replaced.split("/");
            String endTime = deadLines[1].replace("by ", "");
            temp = new deadlineTask(deadLines[0],endTime);
            myTaskList.add(temp);
            spacerForTasks(temp);
        } else if (addedTask.contains("event")&&(addedTask.length()>5)) {
            String replaced = addedTask.replace("event ","");
            String[] events = replaced.split("/");
            String eventTime = events[1].replace("at ", "");
            temp = new eventTask(events[0],eventTime);
            myTaskList.add(temp);
            spacerForTasks(temp);
        }
        else {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        }


    //Retrieve the task, complete it and return the formatted String
    //"Nice! I've marked..."
    public String taskDone(int index) {
        return myTaskList.get(index).taskComplete();
    }

    //Print all tasks upon "list"
    public void printTasks() {
        String separator = "    ____________________________________________________________";
        System.out.println(separator);
        System.out.println("    Here are the tasks in your list:");
        for(int i = 0; i < myTaskList.size(); i++) {
            System.out.println("    " + (i+1) + "." + myTaskList.get(i));
        }
        System.out.println(separator + "\n");

    }

}