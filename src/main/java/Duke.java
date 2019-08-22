import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Integer;

public class Duke {
    public static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                list();
            } else {
                // check if the first word of the line is 'done'
                String[] commandList = command.split(" ");
                if (commandList[0].equals("done")) {
                    done(Integer.valueOf(commandList[1]));
                } else {
                    add(command);
                }
            }
            command = scanner.nextLine();
        }

        exit();
    }

    public static void greet() {
        String message = "Hello! I'm Duke\n     What can I do for you?";
        print(message);
    }

    public static void echo(String message) {
        print(message);
    }

    public static void exit() {
        String message = "Bye. Hope to see you again soon!";
        print(message);
    }

    public static void add(String text) {
        String[] commandList = text.split(" ");
        String taskType = commandList[0];
        String taskText = String.join(" ", Arrays.copyOfRange(commandList, 1, commandList.length));
        Task task;
        if (taskType.equals("todo")) {
            task = addTodo(taskText);
        } else if (taskType.equals("deadline")) {
            task = addDeadline(taskText);
        } else { // (taskType.equals("event"))
            task = addEvent(taskText);
        }
        List<String> list = new ArrayList<>();
        list.add("Got it. I've added this task:");
        list.add(String.format("  %s", task));
        String noun = tasks.size() > 1 ? "tasks" : "task";
        list.add(String.format("Now you have %d %s in the list.", tasks.size(), noun));
        System.out.println(new Message(list));
    }

    public static Todo addTodo(String task) {
        Todo todo = new Todo(task);
        tasks.add(todo);
        return todo;
    }

    public static Deadline addDeadline(String task) {
        String[] attr = task.split(" /by ");
        Deadline deadline = new Deadline(attr[0], attr[1]);
        tasks.add(deadline);
        return deadline;
    }

    public static Event addEvent(String task) {
        String[] attr = task.split(" /at ");
        Event event = new Event(attr[0], attr[1]);
        tasks.add(event);
        return event;
    }

    public static void list() {
        List<String> list = new ArrayList<>();
        list.add("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            String message = String.format("%d.%s", index, task);
            list.add(message);
            index += 1;
        }
        System.out.println(new Message(list));
    }

    public static void done(int num) {
        Task task = tasks.get(num - 1);
        task.markAsDone();
        List<String> list = new ArrayList<>();
        list.add("Nice! I've marked this task as done:");
        list.add(String.format("  %s", task));
        System.out.println(new Message(list));
    }

    public static void print(String message) {
        List<String> list = new ArrayList<>();
        list.add(message);
        System.out.println(new Message(list));
    }
}
