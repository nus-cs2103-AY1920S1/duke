import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> myTaskList;

    public TaskList() {
        myTaskList = new ArrayList<>();
    }

    //Format for printing the "adding"
    private void spacerForTasks(Task inputTask) {
        String separator = "    ____________________________________________________________";
        String addingTask = "    added:";
        String converted = " "+ inputTask.getName();
        System.out.println(separator);
        System.out.printf(addingTask);
        System.out.println(converted);
        System.out.println(separator + "\n");
    }


    //Add and print the added notes
    public void addTasks(String addedTask)  {
        Task temp = new Task(addedTask);
        myTaskList.add(temp);
        spacerForTasks(temp);
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
        for(int i = 0; i < myTaskList.size(); i++) {
            System.out.println("    " + (i+1) + "." + myTaskList.get(i));
        }
        System.out.println(separator + "\n");

    }

}