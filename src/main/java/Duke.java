import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                int index = 0;
                System.out.println("Here are the tasks in your list: ");
                for (Task task : list) {
                    index++;
                    System.out.println(index + ". " + task.toString());
                }
                str = sc.nextLine();
            } else if (str.contains("done")) {
                int index = Integer.valueOf(str.split(" ")[1]);
                list.get(index - 1).complete();
                str = sc.nextLine();
            } else {
                System.out.println("Got it. I've added this task");
                String[] info = str.split("/");
                String[] type = info[0].split(" ");
                Task task;
                System.out.println(Arrays.toString(info));
                System.out.println(Arrays.toString(type));
                switch (type[0]) {
                case "todo":
                    System.out.println("todo");
                    type[0] = "";
                    task = new ToDo(String.join(" ", type).trim());
                    break;
                case "deadline":
                    System.out.println("deadline");
                    type[0] = "";
                    task = new Deadline(String.join(" ", type).trim(), info[1]);
                    break;
                case "event":
                    System.out.println("event");
                    type[0] = "";
                    task = new Event(String.join(" ", type).trim(), info[1]);
                    break;
                default:
                    System.out.println("default");
                    task = new Task(" ");
                    break;
                }
                list.add(task);
                System.out.println("  " + task.toString());
                if (list.size() == 1) {
                    System.out.println("Now you have 1 task in the list");
                } else {
                    System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                }
                str = sc.nextLine();
            }
        }
    }
}

class Task {
    String content;
    boolean done;
    static final int tick = '\u2713';
    static final int cross = '\u2717';

    public Task(String content) {
        this.content = content;
    }

    public void complete() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done: \n  " + this.toString());
    }
}

class ToDo extends Task {

    public ToDo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return done ? String.format("[T][%c] %s", tick, content) : String.format("[T][%c] %s", cross, content);
    }
}

class Deadline extends Task {
    String deadline = "";

    public Deadline(String content, String deadline) {
        super(content);
        String[] tmp = deadline.split(" ");
        tmp[0] = tmp[0] + ":";
        this.deadline = String.join(" ", tmp);
    }

    @Override
    public String toString() {
        return done ? String.format("[D][%c] %s (%s)", tick, content, deadline) : String.format("[D][%c] %s (%s)", cross, content, deadline);
    }
}

class Event extends Task {
    String time = "";

    public Event(String content, String time) {
        super(content);
        String[] tmp = time.split(" ");
        tmp[0] = tmp[0] + ":";
        this.time = String.join(" ", tmp);
    }

    @Override
    public String toString() {
        return done ? String.format("[E][%c] %s (%s)", tick, content, time) : String.format("[E][%c] %s (%s)", cross, content, time);
    }
}