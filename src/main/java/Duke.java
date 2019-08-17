import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    //constants for UI messages
    final static String greet = "Hello! I'm Duke\nWhat can I do for you?";
    final static String goodbye = "Bye. Hope to see you again!";
    final static String niceAdded = "Nice! I've marked this task as done:";


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
                default:
                    //add task logic
                    System.out.println("added: " + command);
                    listOfTasks.add(new Task(command));
                    break;
            }
            command = input.readLine();
        }
        System.out.println(goodbye);
    }
}
