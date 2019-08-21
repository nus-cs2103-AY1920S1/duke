import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);

        // Print initial welcome string
        prettyPrint("Hello! I'm Duke :)\n     What can I do for you?");

        // initialize a TaskList
        TaskList tl = new TaskList();

        // Run till user types "bye"
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                tl.listTasks();
            }
            else {
                String command = input.split(" ")[0];
                switch (command) {
                    case "list":
                        tl.listTasks();
                        break;
                    case "done":
                        try {
                            int index = Integer.parseInt(input.split(" ")[1]);
                            tl.taskDone(index);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            prettyPrint("☹ OOPS!!! Please enter an index to delete.");
                        } finally {
                            break;
                        }
                    case "todo":
                        try {
                            ToDo todo = new ToDo(input.split(" ", 2)[1]);
                            tl.addTask(todo);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            prettyPrint("☹ OOPS!!! The description of a todo cannot be empty.");
                        } finally {
                            break;
                        }
                    case "deadline":
                        try {
                            String deadline_str = input.split(" ", 2)[1];
                            String deadline_name = deadline_str.split(" /by ")[0];
                            String deadline_date = deadline_str.split(" /by ")[1];
                            Deadline deadline = new Deadline(deadline_name, deadline_date);
                            tl.addTask(deadline);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            prettyPrint("☹ OOPS!!! The description of a deadline cannot be empty.");
                        } finally {
                            break;
                        }
                    case "event":
                        try {
                            String event_str = input.split(" ", 2)[1];
                            String event_name = event_str.split(" /at ")[0];
                            String event_date = event_str.split(" /at ")[1];
                            Event event = new Event(event_name, event_date);
                            tl.addTask(event);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            prettyPrint("☹ OOPS!!! The description of an event cannot be empty.");
                        } finally {
                            break;
                        }
                    default:
                        prettyPrint("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        break;
                }
            }
            input = sc.nextLine();
        }

        // Print exit string
        prettyPrint("Bye. Hope to see you again soon!");
    }

    // pretty print a string
    static void prettyPrint(String str) {
        System.out.println("    --------------------------------------------------");
        System.out.println("     " + str);
        System.out.println("    --------------------------------------------------");
    }
}

/** Class to represent a task. */
class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    // mark task as done
    public void markDone() {
        this.done = true;
    }

    // get task name
    public String getName() {
        return this.name;
    }
}

/** Task to represent a To-do */
class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String done_str = this.done ? "✓" : "✗";
        return String.format("[T][%s] %s", done_str, this.name);
    }
}

/** Task to represent a deadline */
class Deadline extends Task {

    protected String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        String done_str = this.done ? "✓" : "✗";
        return String.format("[D][%s] %s (by: %s)", done_str, this.name, this.date);
    }
}

/** Task to represent an event */
class Event extends Task {

    protected String date;

    public Event(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        String done_str = this.done ? "✓" : "✗";
        return String.format("[E][%s] %s (at: %s)", done_str, this.name, this.date);
    }
}


/** Class to represent a list of tasks. */
class TaskList {
    // ArrayList to maintain list of tasks
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    // add tasks
    public void addTask(Task task) {
        tasks.add(task);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n");
        sb.append("       " + task + "\n");
        sb.append(String.format("     Now you have %d tasks in the list.", this.tasks.size()));
        prettyPrint(sb.toString());
    }

    // list tasks
    public void listTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("     %d. %s\n", i+1, this.tasks.get(i).toString()));
        }
        prettyPrint(sb.toString());
    }

    // mark task as done
    public void taskDone(int index) {
        this.tasks.get(index-1).markDone();
        StringBuilder output = new StringBuilder("Nice! I've marked this task as done:\n");
        output.append(String.format("     %s", this.tasks.get(index-1).toString()));
        prettyPrint(output.toString());
    }

    // pretty print a string
    void prettyPrint(String str) {
        System.out.println("    --------------------------------------------------");
        System.out.println("     " + str);
        System.out.println("    --------------------------------------------------");
    }
}
