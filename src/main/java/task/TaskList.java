package task;
import formatter.TimeFormatter;
import java.util.ArrayList;

/**
 *
 */

public class TaskList {
    ArrayList<Task> myTaskList;

    /**
     *
     */

    public TaskList() {
        myTaskList = new ArrayList<>();
    }

    /**
     *
     */

    public ArrayList<Task> getList() {
        return myTaskList;
    }


    /**
     *
     */

    //Add and print the added notes
    public Task addTasks(String addedTask)  {
        Task temp;
        if(addedTask.contains("todo")) {
            String replaced = addedTask.replace("todo ","");
            temp = new todoTask(replaced,false);
            myTaskList.add(temp);
            return temp;
        } else if(addedTask.contains("deadline")) {
            String replaced = addedTask.replace("deadline ","");
            String[] deadLines = replaced.split("by");
            String endTime = deadLines[1];
            temp = new deadlineTask(deadLines[0].replace("/",""),false,TimeFormatter.convertToDate(endTime));
            myTaskList.add(temp);
            return temp;
        } else if (addedTask.contains("event")) {
            String replaced = addedTask.replace("event ","");
            String[] events = replaced.split("at");
            String eventTime = events[1];
            temp = new eventTask(events[0].replace("/",""),false,TimeFormatter.convertToDate(eventTime));
            myTaskList.add(temp);
            return temp;
        }
        return null;
        }


    /**
     *
     */

    //Retrieve the task, complete it and return the formatted String
    //"Nice! I've marked..."
    public Task taskDone(int index) {
        return myTaskList.get(index).taskComplete();
    }


    /**
     *
     */

    public Task deleteTask(int index) {
        Task removed = myTaskList.remove(index);
        return removed;
    }

    /**
     *
     */

    public int getSize() {
        return myTaskList.size();
    }

    }




