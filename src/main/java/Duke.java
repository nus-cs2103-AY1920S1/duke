/* Name: Ang Kai Qi
   MatricNo: A0190206N
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Task> taskList = new ArrayList<Task>();
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.greet();
        Duke.addList();
    }

    static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    static void addList() throws Exception {
        String[] s = Duke.sc.nextLine().split(" ");
        while (!s[0].equals("bye")) {
            if (s[0].equals("list")) {
                Duke.checkList();
            } else {
                Duke.echo(s); //now passing in an array
            }
            s = Duke.sc.nextLine().split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
        Duke.sc.close();
    }

    static void checkList() throws Exception {
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
        ps.println("Here are the tasks in your list:");
        if (taskList.isEmpty()) {
            return;
        }
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            ps.println((i+1) + "." + t);
        }
    }

    static void echo(String[] s) throws Exception {
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
        String[] task = processTask(s);
        try {
            switch (task[0]) {
            case "todo":
                if (validTask(task)) {
                    ToDo td = new ToDo(task[1]);
                    addTask(td);
                }
                break;
            case "deadline":
                if (validTask(task)) {
                    Deadline dl = new Deadline(task[1], task[2]);
                    addTask(dl);
                }
                break;
            case "event":
                if (validTask(task)) {
                    Event e = new Event(task[1], task[2]);
                    addTask(e);
                }
                break;
            case "done":
                task[1] = task[1].substring(1);
                Duke.completed(Integer.parseInt(task[1]));
                break;
            case "delete":
                task[1] = task[1].substring(1);
                Duke.delete(Integer.parseInt(task[1]));
                break;
            default:
                ps.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        } catch (DukeException de) {
            ps.println(de.getMessage());
        }
    }

    static void completed(int i) throws Exception {
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
        Task t = taskList.get(i - 1);
        t.setDone();
        ps.println("Nice! I've marked this task as done: ");
        ps.println("  " + t);
        Duke.save();
    }

    static String[] processTask(String[] s) {
        String[] task = new String[3]; // [todo/deadline/event], [desc], [dateline if applicable];
        task[0] = s[0];
        s[0] = "";
        String s2 = String.join(" ", s);
        String[] s3 = new String[2];
        if (task[0].equals("deadline")) {
            s3 = s2.split("/by");
        } else if (task[0].equals("event")) {
            s3 = s2.split("/at"); // now there might be trailing spaces infront of the words
        } else {
            s3[0] = s2;
        }
        if (s3.length == 2) { // deadline and event
            task[1] = s3[0];
            task[2] = s3[1];
        } else { // todo, done and delete has no date portion, hence task[2] = ""
            task[1] = s3[0];
            task[2] = "";
        }
        return task;
    }

    static void addTask(Task t) throws Exception {
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
        taskList.add(t);
        ps.println("Got it. I've added this task:");
        ps.println("  " + t);
        ps.println("Now you have " + Task.getTotal() + " tasks in the list.");
        Duke.save();
    }

    static boolean validTask(String[] s) throws Exception {
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
        try {
            if (s[1].equals("")) {
                throw new DukeEmptyException(s[0]);
            } else if (!s[0].equals("todo") && s[2].equals("")) {
                throw new DukeEmptyException(s[0], s[0]);
            }
            return true;
        } catch (DukeException de) {
            ps.print("\u2639 OOPS!!! ");
            ps.println(de.getMessage());
            return false;
        }
    }

    static void delete(int i) throws Exception {
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
        Task t = taskList.remove(i - 1);
        ps.println("Noted. I've removed this task:");
        ps.println("  " + t);
        Task.setTotal();
        ps.println("Now you have " + Task.getTotal() + " tasks in the list.");
        Duke.save();
    }

    static void save() throws Exception {
        File file = new File("C:\\Users\\AngKa\\duke\\data\\duke.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        for (Task t : taskList) {
            out.write(t.saveString());
            out.write("\n");
        }
        out.close();
    }
}