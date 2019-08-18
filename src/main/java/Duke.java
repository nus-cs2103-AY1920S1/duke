import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc;
        Boolean exit = false;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        sc = new Scanner(System.in);
        String response = sc.next();
        while (!exit) {
            try {
                if (response.equals("bye")) exit = true;
                else if (response.equals("list")) {
                    Iterator<Task> iter = tasks.iterator();
                    int i = 1;
                    while (iter.hasNext()) {
                        Task task = iter.next();
                        System.out.println(task.toString());
                        i++;
                    }
                    response = sc.next();
                } else if (response.equals("done")) {
                    int taskNo = Integer.parseInt(sc.next());
                    Task task = tasks.get(taskNo - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + task.getStatusIcon() + "] " + task.description);
                    response = sc.next();
                } else if (response.equals("todo")) {
                    try {
                        String description = sc.nextLine().trim();
                        if (description.equals("")) throw new EmptyDescriptionException(
                                "☹ OOPS!!! The description of a todo cannot be empty.");
                        ToDo newToDo = new ToDo(description);
                        tasks.add(newToDo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newToDo.toString());
                        System.out.println("Now you have " + tasks.size() + " task in the list.");
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.toString());
                    }
                    response = sc.next();
                } else if (response.equals("deadline")) {
                    response = sc.nextLine().trim();
                    Deadline newDeadline = new Deadline(response.split(" /by ")[0],
                            response.split(" /by ")[1]);
                    tasks.add(newDeadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    response = sc.next();
                } else if (response.equals("event")) {
                    response = sc.nextLine().trim();
                    Event newEvent = new Event(response.split(" /at ")[0],
                            response.split(" /at ")[1]);
                    tasks.add(newEvent);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    response = sc.next();
                } else if (response.equals("delete")) {
                    int index = Integer.parseInt(sc.nextLine().trim()) - 1;
                    Task removedTask = tasks.remove(index);
                    System.out.println("Got it. I've removed this task:");
                    System.out.println(removedTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    response = sc.next();
                } else {
                    throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (InvalidCommandException e) {
                System.out.println(e.toString());
                response = sc.next();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
