import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Main method for executing Duke.
     *
     *
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String border = "-------------------------------------";

        //Greetings before program
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Hello! I'm Duke\n");
        sb.append("What can I do for you?\n");
        sb.append(border + "\n");
        String greetings = sb.toString();
        System.out.println(greetings);

        //reset Stringbuilder for next message
        sb.setLength(0);
        //Scanner obj for input
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> items = new ArrayList<Task>();

        //Loop till user input 'bye'
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {

            //adding items to arraylist for listing purpose
            if (input.toLowerCase().contains("done")) {
                //getting the number for item
                int itemNum = Integer.parseInt(input.substring(input.length() - 1));
                Task curr = items.get(itemNum - 1);
                curr.markAsDone();
                //forming the message
                sb.append(border + "\n");
                sb.append("Nice! I've marked this task as done:\n");
                sb.append("[" + curr.getStatusIcon() + "]" + curr.getDescription() + "\n");
                sb.append(border + "\n");
                System.out.println(sb.toString());
                sb.setLength(0);
                input = sc.nextLine();
            } else if (input.toLowerCase().equals("list")) {
                sb.append(border + "\n");
                //list out all items in arraylist items
                for (int i = 1; i <= items.size(); i++) {
                    Task curr = items.get(i - 1);
                    sb.append(i + "." + "[" + curr.getStatusIcon() + "]" + " " + curr.getDescription() + "\n");
                }
                sb.append(border + "\n");
                System.out.println(sb.toString());
                sb.setLength(0);
                input = sc.nextLine();
            } else {
                //adding an item
                Task newTask = new Task(input);
                items.add(newTask);
                sb.append(border + "\n");
                sb.append("added: " + input + "\n");
                sb.append(border + "\n");
                System.out.println(sb.toString());
                sb.setLength(0);
                input = sc.nextLine();
            }

        }

        //Concluding Message
        sb.append(border + "\n");
        sb.append("Bye. Hope to see you again soon!\n");
        sb.append(border + "\n");
        String conclude = sb.toString();
        System.out.println(conclude);

    }
}
