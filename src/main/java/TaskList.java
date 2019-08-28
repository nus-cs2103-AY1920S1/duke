import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> myTaskList;

    public TaskList() {

        myTaskList = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return myTaskList;
    }

    //Format for printing the "adding"
    private void spacerForTasks(Task inputTask) {
        String separator = "    ____________________________________________________________";
        String addingTask = "    Got it. I've added this task:";
        String converted = "        "+ inputTask.toString();
        String taskTracking = "    Now you have " + myTaskList.size() + " tasks in the list.";
        System.out.println(separator);
        System.out.println(addingTask);
        System.out.println(converted);
        System.out.println(taskTracking);
        System.out.println(separator + "\n");
    }


    //Add and print the added notes
    public void addTasks(String addedTask) throws DukeException, incompleteInputException {
        Task temp;
        if(addedTask.contains("todo")&&(addedTask.length()>5)) {
            String replaced = addedTask.replace("todo ","");
            temp = new todoTask(replaced,false);
            myTaskList.add(temp);
            spacerForTasks(temp);
        } else if(addedTask.contains("deadline")&&(addedTask.length()>9)&&addedTask.contains("/")) {
            String replaced = addedTask.replace("deadline ","");
            String[] deadLines = replaced.split("by");
            String endTime = deadLines[1];
            temp = new deadlineTask(deadLines[0].replace("/",""),false,TimeFormatter.convertToDate(endTime));
            myTaskList.add(temp);
            spacerForTasks(temp);
        } else if (addedTask.contains("event")&&(addedTask.length()>6)&&addedTask.contains("/")) {
            String replaced = addedTask.replace("event ","");
            String[] events = replaced.split("at");
            String eventTime = events[1];
            temp = new eventTask(events[0].replace("/",""),false,TimeFormatter.convertToDate(eventTime));
            myTaskList.add(temp);
            spacerForTasks(temp);
        } else if(addedTask.contains("todo")) {
            throw new incompleteInputException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        } else if(addedTask.contains("deadline")&&(!(addedTask.contains("/")))) {
            throw new incompleteInputException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        } else if (addedTask.contains("event")&&(!(addedTask.contains("/")))) {
            throw new incompleteInputException("\u2639 OOPS!!! The description of an event cannot be empty.");
        } else {
            throw new DukeException();
        }
        }


    //Retrieve the task, complete it and return the formatted String
    //"Nice! I've marked..."
    public String taskDone(int index) {

        return myTaskList.get(index).taskComplete();
    }

    public void deleteTask(int index) {
        Task removed = myTaskList.remove(index);
        String separator = "    ____________________________________________________________";
        String removingTask = "    Noted. I've removed this task:";
        String converted = "      "+ removed;
        String taskTracking = "    Now you have " + myTaskList.size() + " tasks in the list.";
        System.out.println(separator);
        System.out.println(removingTask);
        System.out.println(converted);
        System.out.println(taskTracking);
        System.out.println(separator + "\n");
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