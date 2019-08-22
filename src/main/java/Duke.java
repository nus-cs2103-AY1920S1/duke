import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String command = sc.next();
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    System.out.println("Here are the " + (tasks.size() == 1 ? "task" : "tasks") + " in your list:");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(i + "." + tasks.get(i - 1));
                    }
                    break;
                case "done":
                    try {
                        int number = sc.nextInt();
                        tasks.get(number - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(number - 1));
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid task name.");
                        sc.nextLine();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid task number.");
                    }
                    break;
                case "todo":
                    try {
                        String taskname = sc.nextLine().trim();
                        if (taskname.equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task t = new Todo(taskname);
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        String deadline = sc.nextLine().trim();
                        if (deadline.equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] arrDeadline = deadline.split("/by");
                        Task t = new Deadline(arrDeadline[0].trim(), arrDeadline[1].trim());
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + t);
                        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        String event = sc.nextLine().trim();
                        if (event.equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        String[] arrEvent = event.split("/at");
                        Task task = new Event(arrEvent[0].trim(), arrEvent[1].trim());
                        tasks.add(task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + task);
                        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    int deletionNumber = sc.nextInt();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + tasks.get(deletionNumber - 1));
                    tasks.remove(deletionNumber - 1);
                    System.out.println("Now you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
                    break;
                default:
                    try {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
    }
}
