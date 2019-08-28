import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> myTaskList;

    public TaskList() {
        myTaskList = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return myTaskList;
    }




    //Add and print the added notes
    public Task addTasks(String addedTask) throws DukeException, incompleteInputException {
        Task temp;
        if(addedTask.contains("todo")&&(addedTask.length()>5)) {
            String replaced = addedTask.replace("todo ","");
            temp = new todoTask(replaced,false);
            myTaskList.add(temp);
            return temp;
        } else if(addedTask.contains("deadline")&&(addedTask.length()>9)&&addedTask.contains("/")) {
            String replaced = addedTask.replace("deadline ","");
            String[] deadLines = replaced.split("by");
            String endTime = deadLines[1];
            temp = new deadlineTask(deadLines[0].replace("/",""),false,TimeFormatter.convertToDate(endTime));
            myTaskList.add(temp);
            return temp;
        } else if (addedTask.contains("event")&&(addedTask.length()>6)&&addedTask.contains("/")) {
            String replaced = addedTask.replace("event ","");
            String[] events = replaced.split("at");
            String eventTime = events[1];
            temp = new eventTask(events[0].replace("/",""),false,TimeFormatter.convertToDate(eventTime));
            myTaskList.add(temp);
            return temp;
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

    public Task deleteTask(int index) {
        Task removed = myTaskList.remove(index);
        return removed;
    }

    public int getSize() {
        return myTaskList.size();
    }

    }




