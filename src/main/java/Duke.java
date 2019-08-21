import java.util.Scanner;
import java.util.ArrayList;

import main.task.Task;

public class Duke {
    public static void main(String[] args) {
        //Level 3
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> events = new ArrayList<Task>();
        String input = sc.nextLine();
        System.out.print("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equals("list")) {
                String result = "";
                for (int i = 0; i < events.size(); i = i + 1) {
                    result = result + "    " + (i + 1) + ". " + events.get(i).toString() + "\n";
                }
                result = result.equals("") ? "\n" : result;
                System.out.print("    ____________________________________________________________\n" +
                        result +
                        "    ____________________________________________________________\n");
                input = sc.nextLine();
                continue;
            } else if (input.substring(0, 4).equals("done")) {
                Scanner sc2 = new Scanner(input);
                sc2.next();
                int taskNumber = sc2.nextInt();
                Task targetedTask = events.get(taskNumber - 1);
                Task.markAsDone(targetedTask);
                sc2.close();
                System.out.print("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       " + targetedTask.toString() +"\n" +
                        "    ____________________________________________________________\n");
                input = sc.nextLine();
            } else {
                Task t = new Task(input);
                System.out.print("    ____________________________________________________________\n" +
                        "    added: "+ input + "\n" +
                        "    ____________________________________________________________\n");
                events.add(t);
                input = sc.nextLine();
            }
        }

        System.out.print("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
