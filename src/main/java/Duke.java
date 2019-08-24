import java.util.Scanner;
import java.util.ArrayList;

import main.task.*;

public class Duke {
    public static void main(String[] args) {
        //Level 8
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        String input = sc.nextLine();
        System.out.print("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        while (!input.equalsIgnoreCase("bye")) {
            if (input.length() == 4 && input.substring(0, 4).equals("list")) {
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
            } else if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                Scanner sc2 = new Scanner(input);
                sc2.next();
                int taskNumber = sc2.nextInt();
                Task targetedTask = tasks.get(taskNumber - 1);
                Task.markAsDone(targetedTask);
                sc2.close();
                System.out.print("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       " + targetedTask.toString() + "\n" +
                        "    ____________________________________________________________\n");
                input = sc.nextLine();
            } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")){
                Scanner sc2 = new Scanner(input);
                sc2.next();
                int taskNumToRemove = sc2.nextInt();
                Task removed = tasks.remove(taskNumToRemove - 1);
                System.out.print("    ____________________________________________________________\n" +
                        "     Noted. I've removed this task: \n" +
                        "       " + removed.toString() + "\n" +
                        "     Now you have " + tasks.size() + " tasks in the list.\n" +
                        "    ____________________________________________________________\n");
                sc2.close();
                input = sc.nextLine();
            } else {
                Task t = null;
                String taskType = "";
                try {
                    if (input.length() > 4 && input.substring(0, 4).equals("todo")) {
                        taskType = "todo";
                        if (input.length() < 6) {
                            throw new InsufficientTaskArgumentException("Not enough arguments for To Do");
                        }
                        t = new ToDo(input.substring(5));
                    } else if (input.length() > 8 && input.substring(0, 8).equals("deadline")) {
                        taskType = "deadline";
                        if (input.length() < 10 || !input.contains("/by")) {
                            throw new InsufficientTaskArgumentException("Not enough arguments for Deadline");
                        }
                        String res = input.substring(9);
                        String[] pair = res.split("/by ");
                        t = new Deadline(pair[0], pair[1]);
                    } else if (input.length() > 5 && input.substring(0, 5).equals("event")) {
                        taskType = "event";
                        if (input.length() < 7 || !input.contains("/at")) {
                            throw new InsufficientTaskArgumentException("Not enough arguments for Deadline");
                        }
                        String res = input.substring(6);
                        String[] pair = res.split("/at ");
                        String[] dateComponents = pair[1].split(" ");
                        t = new Event(pair[0], dateComponents[0], dateComponents[1]);
                    } else {
                        throw new InvalidTaskException(input + " is not a valid task");
                    }

                } catch (InsufficientTaskArgumentException e) {
                    System.out.print("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of a " + taskType + " cannot be empty.\n" +
                            "    ____________________________________________________________\n");
                    input = sc.nextLine();
                    continue;
                } catch (InvalidTaskException e) {
                    System.out.print(e.toString() + "\n");
                    input = sc.nextLine();
                    continue;
                }
                tasks.add(t);
                System.out.print("    ____________________________________________________________\n" +
                            "     Got it. I've added this task: \n" +
                            "       "+ t.toString() + "\n" +
                            "     Now you have " + tasks.size() + " tasks in the list.\n" +
                            "    ____________________________________________________________\n");
                input = sc.nextLine();
            }
        }
        sc.close();
        System.out.print("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
