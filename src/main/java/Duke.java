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
            try {
                switch (tokens[0]) {
                    case "list":
                        //"list" logic
                        int size = listOfTasks.size();
                        System.out.println("Here are your tasks in your list:");
                        for (int i = 0; i < size; i++) {
                            Task curr = listOfTasks.get(i);
                            System.out.println(i + 1 + "." + curr.toString());
                        }
                        break;
                    case "done":
                        try {
                            //done logic
                            int toComplete = Integer.parseInt(command.split(" ")[1]) - 1;
                            if(toComplete >= listOfTasks.size() || toComplete < 0){
                                throw new DukeException("OOPS! Task " + (toComplete + 1) + " doesn't exist!");
                            }
                            Task curr = listOfTasks.get(toComplete);
                            listOfTasks.get(toComplete).completeTask();
                            System.out.println(niceAdded);
                            System.out.println(curr.toString());
                        } catch (DukeException de){
                            System.err.println(de.getMessage());
                        }
                        break;
                    case "todo":
                        try {
                            if (tokens.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            for (int j = 1; j < tokens.length; j++) {
                                toAdd = toAdd + tokens[j] + " ";
                            }
                            newTask = new Todo(toAdd.trim());
                            System.out.println(gotIt);
                            System.out.println(" " + newTask.toString());
                            listOfTasks.add(newTask);
                            System.out.println(printNumTasks());
                        } catch (DukeException de) {
                            System.err.println(de.getMessage());
                        }
                        break;
                    case "deadline":
                        try {
                            if (tokens.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                            }
                            String date = "";
                            boolean dateFlag = false;
                            for (int m = 1; m < tokens.length; m++) {
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
                        } catch (DukeException de){
                            System.err.println(de.getMessage());
                        }
                        break;
                    case "event":
                        try {
                            if(tokens.length == 1){
                                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                            }
                            String timing = " ";
                            boolean timeFlag = false;
                            for (int z = 1; z < tokens.length; z++) {
                                if (tokens[z].equals("/at")) timeFlag = true;
                                else {
                                    if (timeFlag == false) toAdd = toAdd + tokens[z] + " ";
                                    else timing = timing + tokens[z] + " ";
                                }
                            }
                            newTask = new Event(toAdd.trim(), timing.trim());
                            System.out.println(gotIt);
                            System.out.println(" " + newTask.toString());
                            listOfTasks.add(newTask);
                            System.out.println(printNumTasks());
                        } catch (DukeException de){
                            System.err.println(de.getMessage());
                        }
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException de){
                System.err.println(de.getMessage());
            }
            command = input.readLine();
        }
        System.out.println(goodbye);
    }
}
