import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Duke {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        File file = new File("../../../data/duke.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            list.add(parseTaskString(st));
        }

        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                list_events(list);
                str = sc.nextLine();
            } else if (str.contains("done")) {
                done_event(list, str, file);
                str = sc.nextLine();
            } else if (str.contains("delete")) {
                delete_event(list, str, file);
                str = sc.nextLine();
            } else {
                add_event(str, sc, list, file);
                str = sc.nextLine();
            }
        }
    }

    public static void add_event(String str, Scanner sc, ArrayList<Task> list, File file) throws Exception {
        // info splits the string into description and datetime if available
        String[] info = str.split("/");

        // description is the description of whichever item it is
        String[] description = info[0].split(" ");

        Task task;
        try {
            switch (description[0]) {
            case "todo":
                description[0] = "";
                // if description has length 1 it means that only the type of item is there but no description
                if (description.length == 1) {
                    throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                }
                task = new ToDo(String.join(" ", description).trim());
                break;
            case "deadline":
                //take away the deadline from the content
                description[0] = "";
                // if description has length 1 it means that only the type of item is there but no description
                if (description.length == 1) {
                    throw new DukeException("\u2639 OOPS!!! The description of a deadline item cannot be empty.");
                }
                // if it is a deadline event but no slash the info will be just 1 string after split
                if (info.length == 1) {
                    throw new DukeException("\u2639 OOPS!!! You need a /by to separate out the date time for this deadline item.");
                }
                // split the by date
                String[] datetime = info[1].split(" ");
                // make sure they have a by before datetime
                if (!datetime[0].equals("by")) {
                    throw new DukeException("\u2639 OOPS!!! Please follow the format to use /by before datetime");
                }
                // make sure datetime is present
                if (datetime.length <= 1) {
                    throw new DukeException("\u2639 OOPS!!! Please include datetime following the format /by datetime");
                }

                task = new Deadline(String.join(" ", description).trim(), info[1]);
                break;
            case "event":
                description[0] = "";
                // if description has length 1 it means that only the type of item is there but no description
                if (description.length == 1) {
                    throw new DukeException("\u2639 OOPS!!! The description of aa event cannot be empty.");
                }
                // if it is an event but no slash the info will be just 1 string after split
                if (info.length == 1) {
                    throw new DukeException("\u2639 OOPS!!! You need a /by to separate out the date time for this event");
                }
                // split the by date
                datetime = info[1].split(" ");
                // make sure they have a by before datetime
                if (!datetime[0].equals("by")) {
                    throw new DukeException("\u2639 OOPS!!! Please follow the format to use /by before datetime");
                }
                // make sure datetime is present
                if (datetime.length <= 1) {
                    throw new DukeException("\u2639 OOPS!!! Please include datetime following the format /by datetime");
                }

                task = new Event(String.join(" ", description).trim(), info[1]);
                break;
            default:
                // if the user type anything besides the three types of item
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            list.add(task);
            System.out.println("Got it. I've added this task");
            System.out.println("  " + task.toString());
            if (list.size() == 1) {
                System.out.println("Now you have 1 task in the list");
            } else {
                System.out.println(String.format("Now you have %d tasks in the list", list.size()));
            }
            updateFile(list, file);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void list_events(ArrayList<Task> list) {
        int index = 0;
        System.out.println("Here are the tasks in your list: ");
        for (Task task : list) {
            index++;
            System.out.println(index + ". " + task.toString());
        }
    }

    public static void done_event(ArrayList<Task> list, String str, File file) throws Exception {
        int index = Integer.valueOf(str.split(" ")[1]);
        list.get(index - 1).complete();
        updateFile(list, file);
    }

    public static void delete_event(ArrayList<Task> list, String str, File file) throws Exception {
        int index = Integer.valueOf(str.split(" ")[1]);
        Task task = list.get(index - 1);
        list.remove(index - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task.toString());
        if (list.size() == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.println(String.format("Now you have %d tasks in the list", list.size()));
        }
        updateFile(list, file);
    }

    public static void updateFile(ArrayList<Task> list, File file) throws Exception {
        PrintWriter pw = new PrintWriter(file);
        for (Task task : list) {
            pw.println(formatTaskForWriting(task));
        }
        pw.close();
    }

    public static String formatTaskForWriting(Task task) {
        StringBuilder sb = new StringBuilder();
        String className = task.getClass().getSimpleName();
        sb.append(className);
        sb.append("|");
        if (task.done) {
            sb.append("1");
        } else {
            sb.append("0");
        }
        sb.append("|");
        sb.append(task.content);
        if (className.equals("ToDo")) {

        } else {
            sb.append("|");
            if (task instanceof Deadline) {
                sb.append(task.getTime());
            } else {
                sb.append(task.getTime());
            }
        }
        return sb.toString();
    }

    public static Task parseTaskString(String str) {
        String[] strs = str.split("\\|");
        switch (strs[0]) {
        case ("ToDo"):
            Task todo = new ToDo(strs[2]);
            if (strs[1].equals("1")) {
                todo.done = true;
            }
            return todo;
        case ("Deadline"):
            System.out.println(Arrays.toString(strs));
            Task deadline = new Deadline(strs[2], strs[3]);
            if (strs[1].equals("1")) {
                deadline.done = true;
            }
            return deadline;
        case ("Event"):
            Task event = new Event(strs[2], strs[3]);
            if (strs[1].equals("1")) {
                event.done = true;
            }
            return event;
        default:
            return new Task("shouldn't come to here");
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

    public String getTime() {
        return "";
    }

    public String toString() {
        return this.content;
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
        tmp[0] = "";
        this.deadline = String.join(" ", tmp).trim();
    }

    @Override
    public String getTime() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return done ? String.format("[D][%c] %s (by: %s)", tick, content, deadline) : String.format("[D][%c] %s (by: %s)", cross, content, deadline);
    }
}

class Event extends Task {
    String time = "";

    public Event(String content, String time) {
        super(content);
        String[] tmp = time.split(" ");
        tmp[0] = "";
        this.time = String.join(" ", tmp).trim();
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return done ? String.format("[E][%c] %s (by: %s)", tick, content, time) : String.format("[E][%c] %s (by: %s)", cross, content, time);
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}