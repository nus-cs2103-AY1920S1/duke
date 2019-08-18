import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        Task[] tasks = new Task[100];
        int index = 0;
        while (!command.equals("bye")) {
            try {
                if (command.length() < 4) {
                    String error = "\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n";
                    throw new DukeException(error);
                }

                if (!command.equals("list") && !command.substring(0, 4).equals("done")) { //for adding new task
                    if (command.length() >= 8 && !command.substring(0, 4).equals("todo") && !command.substring(0, 5).equals("event") && !command.substring(0, 8).equals("deadline")) {
                        String error = "\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n";
                        throw new DukeException(error);
                    } else if (command.length() >= 5 && command.length() < 8 && !command.substring(0, 4).equals("todo") && !command.substring(0, 5).equals("event")) {
                        String error = "\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n";
                        throw new DukeException(error);
                    } else if (command.length() < 5 && !command.substring(0, 4).equals("todo")) {
                        String error = "\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n";
                        throw new DukeException(error);
                    }
                    if (command.substring(0, 4).equals("todo")) {
                        if (command.length() == 4) { //check to throw for no description
                            String error = "\u2639 OOPS!!! The description of todo cannot be empty.\n";
                            throw new DukeException(error);
                        }
                        tasks[index] = new Todo(command);
                    } else if (command.substring(0, 5).equals("event")) {
                       if (command.length() == 5) { //check to throw for no description
                           String error = "\u2639 OOPS!!! The description of event cannot be empty.\n";
                           throw new DukeException(error);
                       } else if (command.contains(" ")) {
                           String res = command.replaceAll(" ", "");
                           if (res.length() == 5) {
                               String error = "\u2639 OOPS!!! The description of event cannot be empty.\n";
                               throw new DukeException(error);
                           }
                       }
                       if (!command.contains(" /at ")) {//check to throw for no at
                           String error = "\u2639 OOPS!!! You would need to schedule a date and time duration for this event.\n";
                           throw new DukeException(error);
                       }
                        //check to throw for no time range???
                        String[] arr = command.split(" /at ", 2);
                        tasks[index] = new Event(arr[0].substring(6), arr[1]);
                    } else if (command.substring(0, 8).equals("deadline")) {
                        if (command.length() == 8) { //check to throw for no description
                            String error = "\u2639 OOPS!!! The description of deadline cannot be empty.\n";
                            throw new DukeException(error);
                        } else if (command.contains(" ")) {
                            String res = command.replaceAll(" ", "");
                            if (res.length() == 8) {
                                String error = "\u2639 OOPS!!! The description of event cannot be empty.\n";
                                throw new DukeException(error);
                            }
                        }
                        if (!command.contains(" /by ")) {//check to throw for no by
                            String error = "\u2639 OOPS!!! You would need to schedule a date/time for this deadline.\n";
                            throw new DukeException(error);
                        }
                        String[] arr = command.split(" /by ", 2);
                        tasks[index] = new Deadline(arr[0].substring(9), arr[1]);
                    } else { //throw exception for wrong command
                        String error = "\u2639 OOPS!!! I'm sorry but I don't know what that means :-(\n";
                        throw new DukeException(error);
                    }
                    String commandMsg = "Got it. I've added this task:\n"
                            + tasks[index];

                    index++;
                    System.out.println(commandMsg);
                    String statusOfList;
                    if (index == 1) {
                        statusOfList = "Now you have " + index + " task in the list.\n";
                    } else {
                        statusOfList = "Now you have " + index + " tasks in the list.\n";
                    }
                    System.out.println(statusOfList);
                } else if (command.substring(0, 4).equals("done")) { //for marking task as done
                    int curr = Integer.parseInt(command.substring(5));
                    if (curr > index) {//check if index is within list size
                        String error = "\u2639 OOPS!!! You do not have that task in your list. Call 'list' to see all your tasks :-)\n";
                        throw new DukeException(error);
                    }
                    tasks[curr - 1].markAsDone();
                    String markAsDoneMsg = "Nice! I've marked this task as done:\n" +
                            "[" + tasks[curr - 1].getStatusIcon() + "] " + tasks[curr - 1].getDescription() + "\n";
                    System.out.println(markAsDoneMsg);
                } else { //for listing tasks out
                    String listMsg = "Here are the tasks in your list:";
                    System.out.println(listMsg);
                    for (int i = 0; i < index; i++) {
                        Task task = tasks[i];
                        String taskMsg = (i + 1) + ". " + task;
                        System.out.println(taskMsg);
                    }
                    System.out.println();
                }
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                command = sc.nextLine();
            }
        }

        String exitMsg = "Bye. Hope to see you again soon!\n";
        System.out.println(exitMsg);
    }
}
