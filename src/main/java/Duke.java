import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Integer num; //number in list which is done
        Task currTask; //refers to current task in list
        String currEvent;
        ArrayList<Task> list = new ArrayList<Task>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while (!line.equals("bye")) {
            try {
                String desc = ""; //current task being added
                String time = ""; //current time of current task being added
                String[] words = line.split(" ", 2);
                currEvent = words[0];
                if (line.equals("list")) {
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println(i + ". " + list.get(i - 1));
                    }
                } else if (words[0].equals("done")) {
                    num = Integer.valueOf(words[1]);
                    currTask = (Task) list.get(num - 1);
                    currTask.setStatusIcon(true);
                    System.out.println("Nice! I've marked this task as done: \n  " + currTask);
                } else if (currEvent.equals("todo")) {
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    desc = words[1];
                    list.add(new ToDos(desc));
                    System.out.println("Got it. I've added this task: \n  " + list.get(list.size() - 1)
                            + "\nNow you have " + list.size() + " tasks in the list.");
                } else if (currEvent.equals("deadline")) {
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    desc = words[1].split(" /by ", 2)[0];
                    time = words[1].split(" /by ", 2)[1];
                    list.add(new Deadline(desc, time));
                    System.out.println("Got it. I've added this task: \n  " + list.get(list.size() - 1)
                            + "\nNow you have " + list.size() + " tasks in the list.");
                } else if (currEvent.equals("event")) {
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    desc = words[1].split(" /at ", 2)[0];
                    time = words[1].split(" /at ", 2)[1];
                    list.add(new Event(desc, time));
                    System.out.println("Got it. I've added this task: \n  " + list.get(list.size() - 1)
                            + "\nNow you have " + list.size() + " tasks in the list.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException e){
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! The task done must be a number.");
            } finally {
                line = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
