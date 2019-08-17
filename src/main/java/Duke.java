import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String indent = "    ";
        String line = "____________________________________________________________";
        String greeting = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        String bye = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";

        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> command_list = new ArrayList<>();

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(bye);
            } else if (command.equals("list")) {
                int counter = 1;
                System.out.println(indent + line);
                for (String item: command_list) {
                    System.out.println(indent + " " + counter + ". " + item);
                    counter++;
                }
                System.out.println(indent + line + "\n");
            } else {
                System.out.println(
                        indent + line + "\n" +
                                indent + " " + "added: " + command + "\n" +
                                indent + line + "\n"
                );
                command_list.add(command);
            }
        }
        sc.close();

    }
}
