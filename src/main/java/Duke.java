import DukePkg.Task;
import DukePkg.Deadline;
import DukePkg.Event;
import DukePkg.Todo;

import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String greeting = "Hello! I'm Duke\n" +
                          "What can I do for you?";
        System.out.println(greeting);

        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            String command = input.nextLine();
            String arr[] = command.split(" ", 2);
            try{
                switch(arr[0]) {
                    case "list":
                        if (arr.length > 1) {
                            throw new FormatException("☹ OOPS!!! The list command should just be \"list\".");
                        }
                        System.out.println("Here are the tasks in your list:");
                        int counter = 0;
                        for (Task t : tasks) {
                            counter++;
                            System.out.println(counter + ". " + t.toString());
                        }
                        break;
                    case "bye":
                        if (arr.length > 1) {
                            throw new FormatException("☹ OOPS!!! The bye command should just be \"bye\".");
                        }
                        System.out.println("Bye. Hope to see you again soon!");
                        System.exit(0);
                    case "done":
                        if (arr.length == 1 || arr.length > 2) {
                            throw new FormatException("☹ OOPS!!! The done command should be \"done\" + task No.");
                        }
                        int index = Integer.parseInt(arr[1]) - 1;
                        if(index >= tasks.size() || index < 0) {
                            throw new FormatException("☹ OOPS!!! The task No. you refer to is non-existent. Try another one.");
                        }
                        tasks.get(index).markDone();
                        String prompt = "Nice! I've marked this task as done:\n" +
                                "    " + tasks.get(index).toString();
                        System.out.println(prompt);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        if (arr.length == 1) {
                            throw new FormatException("☹ OOPS!!! The description of a " + arr[0] + " cannot be empty.");
                        }
                        Task t = new Task("");
                        t = new Todo(arr[1]);
                        if (!arr[0].equals("todo")) {
                            String ddl[] = arr[1].split("\\B\\/\\w+" + " ", 2);
                            if(ddl.length < 2 || ddl[0].equals("")) {
                                throw new FormatException("☹ OOPS!!! Both description & time of " + arr[0] + " cannot be empty.");
                            }
                            if(arr[0].equals("deadline")) {
                                t = new Deadline(ddl[0], ddl[1]);
                            } else {
                                t = new Event(ddl[0], ddl[1]);
                            }
                        }
                        tasks.add(t);
                        String output = "Got it. I've added this task:\n" +
                                "    " + t.toString() + "\n" +
                                "Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.";
                        System.out.println(output);
                        break;
                    default:
                        throw new UnrecognizedException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(FormatException e) {
                System.out.println(e);
            } catch(UnrecognizedException e) {
                System.out.println(e);
            }
        }
    }
}
