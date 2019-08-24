import java.util.ArrayList;
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

        //Create ArrayList of tasks - dynamic array
        ArrayList<Task> list = new ArrayList<>();

        //Set index of number of task
        int n = 0;

        //Null task for exception
        Task t = null;

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
            input = scanner.nextLine().trim(); //Remove blank space

            //Store whatever text entered, except "bye", exit loop
            if (input.equals("bye")) break;

            System.out.println("    ____________________________________________________________");

            if (input.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < n; i++) {
                    System.out.print("     " + (i+1) + ".");
                    System.out.println(list.get(i));
                }
            }
            else if (input.contains("done")) {
                //Assumption: fixed format - remove first 4 characters to get index. i.e. "done"
                String value = input.substring(4);

                //Get integer found in user input
                int index = Integer.parseInt(value.trim()); //Remove any blank space

              // Optional error handling - if entry does not exist and is marked 'done'
                try {
                    //Mark task as done
                    list.get(index-1).isDone = true;
                }
                catch (NullPointerException err){
                    System.out.println("     List entry does not exist!");
                    System.out.println("    ____________________________________________________________");
                    System.out.println("");
                    continue;
                    }

                System.out.println("     Nice! I've marked this task as done: ");
                System.out.print("       "); //indentation
                System.out.println(list.get(index-1));

            } else if (input.contains("delete")) {
                //Assumption: fixed format - remove first 6 characters to get index. i.e. "delete"
                String value = input.substring(6);

                //Get integer found in user input
                int index = Integer.parseInt(value.trim()); //Remove any blank space
                System.out.println("     Noted. I've removed this task: ");
                System.out.print("       "); //indentation
                System.out.println(list.get(index-1)); //index start from 0

                //Remove task from list
                list.remove(index-1); //index start from 0
                n -= 1; //Remove 1 task from total
                System.out.println("     Now you have " + (n) + " tasks in the list.");
            }

            else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
                String[] substrings = input.split(" ");
                String action = substrings[0];
                try {
                    t = new Task(input, substrings.length);
                }
                //If length is 1, it only has the action but no description
                catch (DukeException err) {
                    System.out.println(err.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println("");
                    continue;
                }

                switch(action) {

                    case "todo":
                        String subT = input.replace("todo", "");
                        list.add(new ToDo(subT.trim())); //Remove blank spaces
                        break;

                    case "deadline":
                        String subD = input.replace("deadline", "");
                        //Split task and deadline
                        String[] partsD = subD.split("\\/by");
                        String descriptionD = partsD[0].trim(); //Remove blank spaces
                        String by = partsD[1].trim(); //Remove blank spaces
                        list.add(new Deadline(descriptionD, by));
                        break;

                    case "event":
                        //Separate description and time for clarity
                        String sub3 = input.replace("event", "");
                        //Split task and deadline
                        String[] partsE = sub3.split("\\/at");
                        String descriptionE = partsE[0].trim(); //Remove blank spaces
                        String at = partsE[1].trim(); //Remove blank spaces
                        list.add(new Event(descriptionE, at));
                        break;
                }

                System.out.println("     Got it. I've added this task:");
                System.out.print("       "); //indentation
                System.out.println(list.get(n));

                //After storing user input into array, increment index
                n += 1;
                System.out.println("     Now you have " + (n) + " tasks in the list.");

            } else {
               //Do not fit any commands
                try {
                    t = new Task(input, 0);
                }
                catch (DukeException err) {
                    System.out.println(err.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println("");
                    continue;
                }
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

