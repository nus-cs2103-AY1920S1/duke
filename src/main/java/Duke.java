import java.util.*;

public class Duke {

    public static ArrayList<Task> storage = new ArrayList<>();

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        printOutput("Hello! I'm Duke\nWhat can i do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")) { //Read input until 'bye' command is entered
            if(input.contains("done")){
                int taskNo = Integer.parseInt(
                                input.replace("done", "")
                                .replace(" ", "")); //Removing 'done' and empty spaces
                Task task = storage.get(taskNo - 1); //Minus 1 because the displayed list starts at 1
                task.markAsDone();

                printOutput("Nice! I've marked this task as done: \n  " + task);
            }
            else if (input.equals("list")) {
                String listOutput = "";
                for (int i = 0; i < storage.size(); i++) {
                    //Get tasks
                    Task task = storage.get(i);

                    listOutput += (i + 1) + "." + task;

                    if (i + 1 != storage.size()) {
                        listOutput += "\n";
                    }
                }
                printOutput(listOutput);
            } else {
                Task newTask = new Task(input);
                storage.add(newTask);
                printOutput("added: " + input);
            }
            input = sc.nextLine();
        }
        printOutput("Bye. Hope to see you again soon!");
    }

    private static void printOutput(String s){
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + s.replace("\n","\n    "));
        System.out.println("    ____________________________________________________________");
    }
}
