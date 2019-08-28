import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String[] parsedString = parseUserInput(str);
        Task task;
        try {
            switch (parsedString[0]) {
            case "todo":
                task = new ToDo(parsedString[1]);
                break;
            case "deadline":
                task = new Deadline(parsedString[1], parsedString[2]);
                break;
            case "event":
                task = new Event(parsedString[1], parsedString[2]);
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

    // this method parse string from txt file and creates task objects when duke is initiated
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

    // this method takes in string from user and decide which type it is and respective information
    // needed to create an object
    // return a string arr of length 3, index 0 is type of task, index 1 is content of task, index 2 is datetime
    // of task if the task requires datetime if not it will be empty string
    public static String[] parseUserInput(String str) throws Exception {
        String[] result = new String[3];
        // info splits the string into description and datetime if available
        String[] info = str.split(" ", 2);

        // if info has length 1 it means that only the type of item is there but no description
        if (info.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of a task cannot be empty.");
        }
        if (info[0].equals("todo")) {
            result[0] = "todo";
            result[1] = info[1];
            result[2] = "nil";
        } else if (info[0].equals("deadline")) {
            String[] dateTimeArr = info[1].split("/by");
            // if it is a deadline event but no slash the info will be just 1 string after split
            if (dateTimeArr.length <= 1) {
                throw new DukeException("\u2639 OOPS!!! You need a /by to separate out the date time for this task.");
            }
            result[0] = "deadline";
            result[1] = dateTimeArr[0];
            result[2] = dateTimeArr[1];
        } else if (info[0].equals("event")) {
            String[] dateTimeArr = info[1].split("/by");
            // if it is a deadline event but no slash the info will be just 1 string after split
            if (dateTimeArr.length <= 1) {
                throw new DukeException("\u2639 OOPS!!! You need a /by to separate out the date time for this task.");
            }
            result[0] = "event";
            result[1] = dateTimeArr[0];
            result[2] = dateTimeArr[1];
        } else {
            throw new DukeException("There is an unknown error parsing your message");
        }
        return result;
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
    Date deadline;
    static SimpleDateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    //static SimpleDateFormat outputFormatter = new SimpleDateFormat("dd of MMM yyyy, hh a");

    public Deadline(String content, String deadline) {
        super(content);
        Date date = new Date();
        try {
            date = inputFormatter.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.deadline = date;
    }

    @Override
    public String getTime() {
        return inputFormatter.format(deadline);
    }

    @Override
    public String toString() {
        return done ? String.format("[D][%c] %s (by: %s)", tick, content, getTime()) : String.format("[D][%c] %s (by: %s)", cross, content, getTime());
    }
}

class Event extends Task {
    Date time;
    static SimpleDateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    //static SimpleDateFormat outputFormatter = new SimpleDateFormat("dd of MMM yyyy, hh a");

    public Event(String content, String time) {
        super(content);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date = new Date();
        try {
            date = formatter.parse(time);
            System.out.println(date);
            System.out.println(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.time = date;
    }

    @Override
    public String getTime() {
        return inputFormatter.format(time);
    }

    @Override
    public String toString() {
        return done ? String.format("[E][%c] %s (by: %s)", tick, content, getTime()) : String.format("[E][%c] %s (by: %s)", cross, content, getTime());
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}