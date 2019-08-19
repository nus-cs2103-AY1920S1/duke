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
                    System.out.println(list[i].toString());
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
                System.out.println(list[index-1].toString());

            } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
                String[] substrings = input.split(" ");
                String action = substrings[0];
                switch(action) {

                    case "todo":
                        String subT = input.replace("todo", "");
                        list[n] = new ToDo(subT.trim()); //Remove blank spaces
                        break;

                    case "deadline":
                        String subD = input.replace("deadline", "");
                        //Split task and deadline
                        String[] partsD = subD.split("\\/by");
                        String descriptionD = partsD[0].trim(); //Remove blank spaces
                        String by = partsD[1].trim(); //Remove blank spaces
                        list[n] = new Deadline(descriptionD, by);
                        break;

                    case "event":
                        //Separate description and time for clarity
                        String sub3 = input.replace("event", "");
                        //Split task and deadline
                        String[] partsE = sub3.split("\\/at");
                        String descriptionE = partsE[0].trim(); //Remove blank spaces
                        String at = partsE[1].trim(); //Remove blank spaces
                        list[n] = new Event(descriptionE, at);
                        break;
                }

                System.out.println("     Got it. I've added this task:");
                System.out.print("       "); //indentation
                System.out.println(list[n].toString());

                //After storing user input into array, increment index
                n += 1;
                System.out.println("     Now you have " + (n) + " tasks in the list.");

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

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

}

class ToDo extends Task {

    protected String by;

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}