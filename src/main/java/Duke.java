import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class Duke {
    public static void main(String[] args) {
        String dividerLine = new String("\u2501").repeat(80).concat("\n");
        String startMessage = "  Hello! I'm Duke\n"
                + "  What can I do for you?\n";
        String logo = "   ____        _        \n"
                + "  |  _ \\ _   _| | _____ \n"
                + "  | | | | | | | |/ / _ \\\n"
                + "  | |_| | |_| |   <  __/\n"
                + "  |____/ \\__,_|_|\\_\\___|\n"
                + startMessage
                + dividerLine;

        LinkedList<String> tasks = new LinkedList<>();

        System.out.print(dividerLine + "  Hello from\n" + logo);

        //Take in input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        //Check if bye
        while (!input.equals("bye")) {
            //If list print all tasks
            if (input.equals("list")) {
                System.out.print(dividerLine);

                //Print each task
                Iterator<String> tasksIterator = tasks.iterator();

                for (int i = 0; i < tasks.size(); i++) {
                    System.out.print("  "
                            + (i+1)
                            + "."
                            + tasksIterator.next()
                            + "\n");
                }

                System.out.print(dividerLine);
            }
            //Else add input to array list
            //and echo that input is added
            else {
                tasks.add(input);
                System.out.print(dividerLine
                        + "  added "
                        + input
                        + "\n"
                        + dividerLine);
            }

            input = sc.nextLine();
        }
        // Exit if bye
        System.out.println(dividerLine
                + "  Bye. Hope to see you again soon!\n"
                + dividerLine);

    }
}
