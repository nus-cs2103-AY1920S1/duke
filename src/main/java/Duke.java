import java.util.Scanner;

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
        Task tasks[] = new Task[100];
        int task_count = 0;

        while (!input.equals("bye")) {
            try{
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < task_count; i++) {
                        Task t = tasks[i];
                        System.out.println((i + 1) + "." + t.toString());
                    }
                } else if (input.substring(0, 4).equals("done")) {
                    int do_Index = Integer.parseInt(input.substring(5)) - 1;
                    Task chosen_Task = tasks[do_Index];
                    chosen_Task.markAsDone();

                    System.out.println("Nice! I've marked this task as done:\n" +
                            chosen_Task.toString());
                } else {
                    if (input.substring(0, 4).equals("todo")) {
                        if (input.length() == 4) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasks[task_count] = new ToDo(input.substring(5));
                    } else if (input.substring(0, 5).equals("event")) {
                        if (input.length() == 5) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        int i = input.indexOf('/');
                        tasks[task_count] = new Event(input.substring(6, i - 1), input.substring(i + 4));
                    } else if (input.substring(0, 8).equals("deadline")) {
                        if (input.length() == 8) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        int i = input.indexOf('/');
                        tasks[task_count] = new Deadline(input.substring(9, i - 1), input.substring(i + 4));
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[task_count].toString());
                    System.out.println("Now you have " + (task_count + 1) +
                            (task_count == 0 ? " task" : " tasks") + " in the list.");
                    task_count++;
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
