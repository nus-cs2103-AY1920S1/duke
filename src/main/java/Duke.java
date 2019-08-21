import java.util.Scanner;
import java.util.ArrayList;

import main.task.*;

public class Duke {
    public static void main(String[] args) {
        //Level 3
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        String input = sc.nextLine();
        System.out.print("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equals("list")) {
                String result = "";
                for (int i = 0; i < tasks.size(); i = i + 1) {
                    result = result + "    " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
                }
                result = result.equals("") ? "\n" : result;
                System.out.print("    ____________________________________________________________\n" +
                        "    Here are the tasks in your list:\n" +
                        result +
                        "    ____________________________________________________________\n");
                input = sc.nextLine();
                continue;
            } else if (input.substring(0, 4).equals("done")) {
                Scanner sc2 = new Scanner(input);
                sc2.next();
                int taskNumber = sc2.nextInt();
                Task targetedTask = tasks.get(taskNumber - 1);
                Task.markAsDone(targetedTask);
                sc2.close();
                System.out.print("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       " + targetedTask.toString() +"\n" +
                        "    ____________________________________________________________\n");
                input = sc.nextLine();
            } else {
                Task t;
                if (input.substring(0, 4).equals("todo")) {
                    t = new ToDo(input.substring(5));
                } else if (input.substring(0, 8).equals("deadline")) {
                    String res = input.substring(9);
                    String[] pair = res.split("/");
                    t = new Deadline(pair[0], pair[1]);
                } else {
                    String res = input.substring(6);
                    String[] pair = res.split("/");
                    t = new Event(pair[0], pair[1]);
                }
                System.out.print("    ____________________________________________________________\n" +
                        "     Got it. I've added this task: \n" +
                        "       "+ t.toString() + "\n" +
                        "     Now you have " + tasks.size() + " tasks in the list.\n" +
                        "    ____________________________________________________________\n");
                tasks.add(t);
                input = sc.nextLine();
            }
        }

        System.out.print("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
