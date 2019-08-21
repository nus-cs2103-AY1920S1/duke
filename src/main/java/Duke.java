import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<String>();
        String lines = "    ____________________________________________________________\n";
        String indent = "    ";
        String logo = lines
                    + "     ____        _           \n"
                    + "    |  _ \\ _   _| | _____   \n"
                    + "    | | | | | | | |/ / _ \\  \n"
                    + "    | |_| | |_| |   <  __/   \n"
                    + "    |____/ \\__,_|_|\\_\\___|\n"
                    + "    Hello! I'm Duke          \n"
                    + "    What can I do for you?   \n"
                    + lines;
        System.out.println(logo);
        String comd = sc.nextLine();
        while (!comd.equals("bye")) {
            if (comd.isEmpty()) {}
            else if (comd.equals("list")) {
                System.out.print(lines);
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(indent + (i + 1) + ". " + taskList.get(i));
                }
                System.out.println(lines);
            } else {
                taskList.add(comd);
                System.out.print(lines);
                System.out.println(indent + "added: " + comd);
                System.out.println(lines);
            }
            comd = sc.nextLine();
        }
        System.out.println(lines + indent + "Bye. Hope to see you again soon!\n" + lines);
    }
}
