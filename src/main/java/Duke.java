import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();
        String welcomeMessage = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(welcomeMessage);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("    ____________________________________________________________\n"
                        + "     Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("     " + i + "." + list.get(i-1));
                }
                System.out.println("    ____________________________________________________________\n");
            } else {
                String[] inputStringArray = input.split("\\s+");
                if (inputStringArray[0].equals("done")) {
                    list.get(Integer.parseInt(inputStringArray[1]) - 1).markAsDone();
                } else {
                    Task task = new Task(input);
                    list.add(task);
                    System.out.println("    ____________________________________________________________\n"
                            + "     added: " + input + "\n"
                            + "    ____________________________________________________________\n");
                }
            }
            input = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n");
    }
}
