import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner sc;
        Boolean exit = false;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        sc = new Scanner(System.in);
        String response = sc.nextLine();
        while (!exit) {
            if (response.equals("bye")) exit = true;
            else if (response.equals("list")) {
                Iterator<String> iter = tasks.iterator();
                int i = 1;
                while (iter.hasNext()) {
                    System.out.println(i + ": " + iter.next());
                    i++;
                }
                response = sc.nextLine();
            }
            else {
                tasks.add(response);
                System.out.println("added: " + response);
                response = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
