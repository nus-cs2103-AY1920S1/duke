import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner scn = new Scanner(System.in);

        String input = scn.nextLine();
        List<Task> tasks = new ArrayList<Task>();

        while (!input.equals("bye")) {
            try{
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    int index = 0;
                    for (Task t : tasks) {
                        System.out.println((index + 1) + "." + t.toString());
                        index++;
                    }
                } else if (input.substring(0, 4).equals("done")) {
                    int do_Index = Integer.parseInt(input.substring(5)) - 1;
                    Task chosen_Task = tasks.get(do_Index);
                    chosen_Task.markAsDone();

                    System.out.println("Nice! I've marked this task as done:\n  " +
                            chosen_Task.toString());
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    int delete_Index = Integer.parseInt(input.substring(7)) - 1;
                    Task chosen_Task = tasks.get(delete_Index);
                    tasks.remove(delete_Index);

                    System.out.println("Noted. I've removed this task:\n  " + chosen_Task.toString());
                    System.out.println("Now you have " + tasks.size() +
                            (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                } else {
                    if (input.substring(0, 4).equals("todo")) {
                        if (input.length() == 4) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasks.add(new ToDo(input.substring(5)));
                    } else if (input.substring(0, 5).equals("event")) {
                        if (input.length() == 5) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        int i = input.indexOf('/');
                        tasks.add(new Event(input.substring(6, i - 1), input.substring(i + 4)));
                    } else if (input.substring(0, 8).equals("deadline")) {
                        if (input.length() == 8) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        int i = input.indexOf('/');
                        tasks.add(new Deadline(input.substring(9, i - 1), input.substring(i + 4)));
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() +
                            (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                }
            }
            catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            catch (StringIndexOutOfBoundsException ex) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            input = scn.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
