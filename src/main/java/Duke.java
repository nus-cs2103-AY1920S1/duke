import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    private static  LinkedList<String> tasks = new LinkedList<String>();
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         */
        System.out.println("Hello I'm Duke\n" + "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String curr = sc.nextLine();
            if (curr.equals("bye")) {
               System.out.println("Bye. Hope to see you again soon!");
               break;
            } else {
                execute(curr);
            }
        }

    }

    private static void execute(String command) {
        if (command.equals("list")) {
            int i = 1;
            for (String s: tasks) {
                System.out.println(i + ". " + s);
                i++;
            }
        } else {
            tasks.add(command);
            System.out.println("added: " + command);
        }
    }


}
