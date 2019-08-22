import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        String[] arr = sc.nextLine().split(" ");
        String next = arr[0];
        int count = 0;
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                int listCount = 1;
                for (Task task : list) {
                    System.out.println(listCount + "." + task);
                    listCount++;
                }
            } else if (next.equals("done")) {
                int number = Integer.parseInt(arr[1]) - 1;
                Task taskDone = list.get(number);
                taskDone.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + taskDone);
            } else {
                count++;
                String description = "";
                Task t;
                try {
                    if (next.equals("todo") || next.equals("deadline") || next.equals("event")) {
                        if (arr.length == 1) {
                            throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + next + " cannot be empty.");
                        } else {
                            for (int i = 1; i < arr.length; i++) {
                                description += " " + arr[i];
                            }
                        }
                    } else {
                        throw new UnknownTaskException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Got it. I've added this task:");
                    if (next.equals("todo")) {
                        t = new Todos(description.trim());
                    } else if (next.equals("deadline")) {
                        int index = description.indexOf("/");
                        String byWhen = description.substring(index + 4, description.length());
                        String desc = description.substring(1, index - 1);
                        t = new Deadline(desc, byWhen);
                    } else {
                        int index = description.indexOf("/");
                        String at = description.substring(index + 4, description.length());
                        String desc = description.substring(1, index - 1);
                        t = new Event(desc, at);
                    }
                    list.add(t);
                    if (count == 1) {
                        System.out.println("  " + t + "\nNow you have " + count + " task in the list.");
                    } else {
                        System.out.println("  " + t + "\nNow you have " + count + " tasks in the list.");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            arr = sc.nextLine().split(" ");
            next = arr[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}