import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = "___________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________";
        String bye = "____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        String echo = sc.nextLine();

        while (echo != null) {
            if (echo.equals("bye")) {
                System.out.println(bye);
                break;

            } else if (echo.equals("list")) {
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list:");
                int count = 1;
                for (Task t : taskList) {
                    System.out.println(count + "." + t.getDescription());
                    //System.out.printf("[%s]%d. %s%n", t.getStatusIcon(), count, t.description);
                    count++;
                }
                System.out.println("____________________________________________________________\n");
                echo = sc.nextLine();

            } else {
                Task newTask = new Task(echo);
                taskList.add(newTask);
                System.out.println("____________________________________________________________\n" +
                        "added: " + echo + "\n" +
                        "____________________________________________________________");
                echo = sc.nextLine();
            }
        }
    }
}
