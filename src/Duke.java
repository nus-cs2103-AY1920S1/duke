import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {



    private static String divider = "\t____________________________________________________________\n";
    private static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|q\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.print(divider);
        System.out.print("\t Hello! I'm Duke\n");
        System.out.print("\t What can I do for you?\n");
        System.out.print(divider);

        while (true) {
            Scanner sc = new Scanner(System.in);
            String action = sc.next();
            if (action.equals("bye")) {
                System.out.print(divider);
                System.out.print("\t Bye. Hope to see you again soon!\n");
                System.out.print(divider);
                break;

            } else if (action.equals("list")) {
                System.out.print(divider);
                String header = "Here are the tasks in your list:";
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.printf("\t %d.", i+1);
                    System.out.printf("%s\n", taskList.get(i));
                }
                System.out.print(divider);

            } else if (action.equals("done")) {
                int idx = sc.nextInt() - 1;
                taskList.get(idx).setDone();

                System.out.print(divider);
                System.out.print("\t Nice! I've marked this task as done:\n");
                System.out.printf("\t\t%s\n", taskList.get(idx));
                System.out.print(divider);


            } else if (action.equals("todo")) {
                String task = sc.nextLine();
                Task newTask = new Todo(task);
                taskList.add(newTask);

                System.out.print(divider);
                System.out.print("\t Got it. I've added this task:\n");
                System.out.printf("\t\t %s\n", newTask);
                System.out.printf("\t Now you have %d tasks in the list.\n", taskList.size());
                System.out.print(divider);

            } else if (action.equals("deadline")) {
                String task = sc.nextLine();
                String[] taskInfo = task.split("\\s*/by\\s*");
                Task newTask = new Deadline(taskInfo[0], taskInfo[1]);
                taskList.add(newTask);

                System.out.print(divider);
                System.out.print("\t Got it. I've added this task:\n");
                System.out.printf("\t\t %s\n", newTask);
                System.out.printf("\t Now you have %d tasks in the list.\n", taskList.size());
                System.out.print(divider);

            } else if (action.equals("event")) {
                String task = sc.nextLine();
                String[] taskInfo = task.split("\\s*/at\\s*");
                Task newTask = new Event(taskInfo[0], taskInfo[1]);
                taskList.add(newTask);

                System.out.print(divider);
                System.out.print("\t Got it. I've added this task:\n");
                System.out.printf("\t\t %s\n", newTask);
                System.out.printf("\t Now you have %d tasks in the list.\n", taskList.size());
                System.out.print(divider);

            }
        }

    }



}
