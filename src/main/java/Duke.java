import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Store command-line input as String
        String input = "";

        //Create array of tasks
//        String[] list = new String[100];
        Task list[]= new Task[100];

        //Set index of number of task
        int n = 0;

        //Read command-line input with Scanner
        Scanner scanner = new Scanner(System.in);

        //Initial opening introduction and prompt for user input
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println("");

        //Check for last statement
        while (!input.equals("bye")) {

            // Get entire line of input from command-line
            input = scanner.nextLine();

            //Store whatever text entered, except "bye", exit loop
            if (input.equals("bye")) break;

            System.out.println("    ____________________________________________________________");

            if (input.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < n; i++) {
                    System.out.print("     " + (i+1) + ".");
                    Task.printTask(list[i].getStatusIcon(), list[i].description);
                }
            }
            else if (input.contains("done")) {
                //Assumption: fixed format - remove first 5 characters to get index. i.e. "done_"
                String value = input.substring(5);

                //Get integer found in user input
                int index = Integer.parseInt(value);

                //Mark task as done
                list[index-1].isDone = true;

                System.out.println("     Nice! I've marked this task as done: ");
                System.out.print("       ");
                Task.printTask(list[index-1].getStatusIcon(), list[index-1].description);

            } else {
                //Create task as a object and store in array
                Task t = new Task(input);
                list[n] = t;

                System.out.println("     added: " + input);

                //After storing user input into array, increment index
                n += 1;
            }
            System.out.println("    ____________________________________________________________");
            System.out.println("");

        }
        //Closing statement
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //Print specific task with its status
    public static void printTask(String status, String description) {
        System.out.println("[" + status + "] " + description);
    }
}