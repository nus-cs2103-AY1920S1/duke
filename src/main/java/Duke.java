import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    //constants for UI messages
    final static String greet = "Hello! I'm Duke\nWhat can I do for you?";
    final static String goodbye = "Bye. Hope to see you again!";
    final static String niceAdded = "Nice! I've marked this task as done:";
    final static String gotIt = "Got it. I've added this task:";

    private static String printNumTasks(){
        return "Now you have " + Task.totalTasks + " tasks in the list.";
    }

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> listOfTasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greet);

        String command = input.readLine();

        while(!command.equals("bye")){
            String[] tokens = command.split(" ");
            String toAdd = "";
            Task newTask;
            switch (tokens[0])
            {
                case "list":
                    //"list" logic
                    int size = listOfTasks.size();
                    System.out.println("Here are your tasks in your list:");
                    for(int i = 0; i < size; i++){
                        Task curr = listOfTasks.get(i);
                        System.out.println(i+1 + "." + curr.toString());
                    }
                    break;
                case "done":
                    //done logic
                    int toComplete = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task curr = listOfTasks.get(toComplete);
                    listOfTasks.get(toComplete).completeTask();
                    System.out.println(niceAdded);
                    System.out.println(curr.toString());
                    break;
                case "todo":
                    for(int j = 1; j < tokens.length; j++){
                        toAdd = toAdd + tokens[j] + " ";
                    }
                    newTask = new Todo(toAdd.trim());
                    System.out.println(gotIt);
                    System.out.println(" " + newTask.toString());
                    listOfTasks.add(newTask);
                    System.out.println(printNumTasks());
                    break;
                case "deadline":
                    String date = "";
                    boolean dateFlag = false;
                    for(int m = 1; m < tokens.length; m++) {
                        if (tokens[m].equals("/by")) {
                            dateFlag = true;
                        } else {
                            if (dateFlag == false) toAdd = toAdd + tokens[m] + " ";
                            else date = date + tokens[m] + " ";
                        }
                    }
                    newTask = new Deadline(toAdd.trim(), date.trim());
                    System.out.println(gotIt);
                    System.out.println(" " + newTask.toString());
                    listOfTasks.add(newTask);
                    System.out.println(printNumTasks());
                    break;
                case "event":
                    String timing = " ";
                    boolean timeFlag = false;
                    for(int z = 1; z < tokens.length; z++){
                        if(tokens[z].equals("/at")) timeFlag = true;
                        else {
                            if(timeFlag == false) toAdd = toAdd + tokens[z] + " ";
                            else timing = timing + tokens[z] + " ";
                        }
                    }
                    newTask = new Event(toAdd.trim(), timing.trim());
                    System.out.println(gotIt);
                    System.out.println(" " + newTask.toString());
                    listOfTasks.add(newTask);
                    System.out.println(printNumTasks());
                    break;
                default:
                    break;
            }
            command = input.readLine();
        }
        System.out.println(goodbye);
    }
}
