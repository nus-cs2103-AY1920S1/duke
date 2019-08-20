import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greets user
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");


        Scanner scan = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        String input = scan.nextLine();
        while (!input.equals("bye")) {
            //Adding the command "list" to list all added tasks
            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
                input = scan.nextLine();
            } else {
                //Adds input to list
                list.add(input);
                System.out.println("added: " + input);
                input = scan.nextLine();
            }
        }

        //Exit
        System.out.println("Bye. Hope to see you again soon!");

    }
}
