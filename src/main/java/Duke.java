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
        ArrayList<String> items = new ArrayList<String>();

        //Loop till user input 'bye'
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {

            //adding items to arraylist for listing purpose
            if (!input.toLowerCase().equals("list")) {
                items.add(input);
                sb.append(border + "\n");
                sb.append("added: " + input + "\n");
                sb.append(border + "\n");
                System.out.println(sb.toString());
                sb.setLength(0);
                input = sc.nextLine();
            } else {
                sb.append(border + "\n");
                //list out all items in arraylist items
                for (int i = 1; i <= items.size(); i++) {
                    sb.append(i + ". " + items.get(i - 1) + "\n");
                }
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
