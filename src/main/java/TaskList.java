import java.util.ArrayList;

public class TaskList {
    ArrayList<String> myTaskList;

    public TaskList() {
        myTaskList = new ArrayList<>();
    }
    //Format for printing the "adding"
    private void spacerForTasks(String input) {
        String separator = "    ____________________________________________________________";
        String addingTask = "    added:";
        String converted = " "+ input;
        System.out.println(separator);
        System.out.printf(addingTask);
        System.out.println(converted);
        System.out.println(separator + "\n");
    }
    //Add and print the added notes
    public void addTasks(String addedTask)  {
        myTaskList.add(addedTask);
        spacerForTasks(addedTask);
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