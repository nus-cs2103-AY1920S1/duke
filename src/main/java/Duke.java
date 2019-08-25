import java.io.BufferedWriter;
import java.text.ParseException;
import java.io.IOException;
import java.io.Writer;
import java.nio.Buffer; 
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Duke {
    // String Constants used for Duke output
    private static final String INTRODUCTION = "     Hello! I'm Duke";
    private static final String USER_PROMPT = "     What can I do for you?";
    private static final String SEPARATOR = "    ____________________________________________________________";
    private static final String SHOW_LIST = "     Here are the tasks in your list:";
    private static final String INVALID_LIST_ENTRY = "     List entry does not exist!";
    private static final String MARK_TASK_COMPLETE = "     Nice! I've marked this task as done: ";
    private static final String ADD_TASK = "     Got it. I've added this task:";
    private static final String REMOVE_TASK = "     Noted. I've removed this task: ";
    private static final String CLOSING_STATEMENT = "     Bye. Hope to see you again soon!";

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Store command-line input as String
        String input = "";

        // Create ArrayList of tasks - dynamic array
        ArrayList<Task> list = new ArrayList<>();

        // Set index of number of task
        int n = 0;

        // Null task for exception
        Task t = null;

        ListFile lf = new ListFile();
        lf.openFile();
        list = lf.readAndWriteList(list); // add existing tasks from txt file onto ArrayList
        n = list.size(); // get existing size of list
        lf.closeFile();

        // Read command-line input with Scanner
        Scanner scanner = new Scanner(System.in);

        // Initial opening introduction and prompt for user input
        System.out.println(SEPARATOR);
        System.out.println(INTRODUCTION);
        System.out.println(USER_PROMPT);
        System.out.println(SEPARATOR);
        System.out.println("");

        // Check for last statement
        while (!input.equals("bye")) {

            // Get entire line of input from command-line
            input = scanner.nextLine().trim(); //Remove blank space

            // Store whatever text entered, except "bye", exit loop
            if (input.equals("bye")) break;

            System.out.println(SEPARATOR);

            if (input.equals("list")) {
                System.out.println(SHOW_LIST);
                for (int i = 0; i < n; i++) {
                    System.out.print("     " + (i+1) + ".");
                    System.out.println(list.get(i));
                }
            }
            else if (input.contains("done")) {
                // Assumption: fixed format - remove first 4 characters to get index. i.e. "done"
                String value = input.substring(4);

                // Get integer found in user input
                int index = Integer.parseInt(value.trim()); //Remove any blank space

              // Optional error handling - if entry does not exist and is marked 'done'
                try {
                    //Mark task as done
                    list.get(index-1).isDone = true;
                }
                catch (NullPointerException err){
                    System.out.println(INVALID_LIST_ENTRY);
                    System.out.println(SEPARATOR);
                    System.out.println("");
                    continue;
                    }

                System.out.println(MARK_TASK_COMPLETE);
                System.out.print("       "); //indentation
                System.out.println(list.get(index-1));

            } else if (input.contains("delete")) {
                // Assumption: fixed format - remove first 6 characters to get index. i.e. "delete"
                String value = input.substring(6);

                // Get integer found in user input
                int index = Integer.parseInt(value.trim()); //Remove any blank space
                System.out.println(REMOVE_TASK);
                System.out.print("       "); //indentation
                System.out.println(list.get(index-1)); //index start from 0

                // Remove task from list
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
                // If length is 1, it only has the action but no description
                catch (DukeException err) {
                    System.out.println(err.getMessage());
                    System.out.println(SEPARATOR);
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
                        String descriptionD = partsD[0].trim(); // Remove blank spaces
                        String by = partsD[1].trim(); // Remove blank spaces
                        list.add(new Deadline(descriptionD, by));
                        break;

                    case "event":
                        //Separate description and time for clarity
                        String sub3 = input.replace("event", "");
                        //Split task and deadline
                        String[] partsE = sub3.split("\\/at");
                        String descriptionE = partsE[0].trim(); // Remove blank spaces
                        String at = partsE[1].trim(); // Remove blank spaces
                        list.add(new Event(descriptionE, at));
                        break;
                }

                System.out.println(ADD_TASK);
                System.out.print("       "); // indentation
                System.out.println(list.get(n));

                // After storing user input into array, increment index
                n += 1;
                System.out.println("     Now you have " + (n) + " tasks in the list.");

            } else {
               // Do not fit any commands
                try {
                    t = new Task(input, 0);
                }
                catch (DukeException err) {
                    System.out.println(err.getMessage());
                    System.out.println(SEPARATOR);
                    System.out.println("");
                    continue;
                }
            }

            System.out.println(SEPARATOR);
            System.out.println("");

            FileWriter fw = new FileWriter("list.txt");
            BufferedWriter output = new BufferedWriter(fw);
            for (int i=0; i<n; i++) {
                output.write(list.get(i).toSave());
                output.newLine();
            }
            output.close();
        }
        // Closing statement
        System.out.println(SEPARATOR);
        System.out.println(CLOSING_STATEMENT);
        System.out.println(SEPARATOR);
    }
}

