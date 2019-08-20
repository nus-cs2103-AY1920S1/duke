import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> todolist = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        //System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        try {
            while (scanner.hasNext()) {
                String request = scanner.nextLine();

                System.out.println("____________________________________________________________");
                if (request.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    return;
                } else if (request.equals("list")) {
                    for (int i = 0; i < todolist.size(); i++) {
                        String todo = String.format("%d. %s", i + 1, todolist.get(i).toString());
                        System.out.println(todo);
                    }
                } else if (request.startsWith("done")) {
                    char num = request.charAt(request.length() - 1);
                    int index = Character.getNumericValue(num);
                    Task task = todolist.get(index - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.print("  ");
                    System.out.println(task.toString());
                } else if (request.startsWith("todo")) {
                    if (request.trim().length() == 4) {
                        throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String descrip = request.substring(5);
                    Task task = new Todo(descrip);
                    todolist.add(task);
                    String add = String.format("added: %s", request);
                    addTask(task);
                } else if (request.startsWith("event")) {
                    if (request.trim().length() == 5) {
                        throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String[] event = request.substring(6).split(" /at ");
                    if (event.length != 2) {
                        throw new DukeException(" ☹ OOPS!!! The description of a event is wrong.");
                    }
                    Task task = new Event(event[0], event[1]);
                    todolist.add(task);
                    addTask(task);
                } else if (request.startsWith("deadline")) {
                    if (request.trim().length() == 8) {
                        throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] event = request.substring(9).split(" /by ");
                    if (event.length != 2) {
                        throw new DukeException(" ☹ OOPS!!! The description of a event is wrong.");
                    }
                    Task task = new Deadline(event[0], event[1]);
                    todolist.add(task);
                    addTask(task);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                System.out.println("____________________________________________________________");
            }

        } catch (DukeException exception) {
            System.out.println(exception.toString());
        }
    }

    private static void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", todolist.size()));
    }
}