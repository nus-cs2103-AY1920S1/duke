/* Name: Ang Kai Qi
   MatricNo: A0190206N
 */
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
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
        String[] s = Duke.sc.nextLine().split(" ");
        while (!s[0].equals("bye")) {
            switch (s[0]) {
                case "list":
                    Duke.checkList();
                    break;
                case "done":
                    ps.println(Duke.completed(2));
                    break;
                default:
                    Duke.echo(String.join(" ", s));
                    break;
            }
            s = Duke.sc.nextLine().split(" ");
        }
        ps.println("Bye. Hope to see you again soon!");
        Duke.sc.close();
    }

    static void checkList() throws Exception {
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
        ps.println("Here are the tasks in your list:");
        if (taskList.isEmpty()) {
            return;
        }
        for (Task t : taskList) {
            ps.println(t.getIdx() + "." + t);
        }
    }

    static void echo(String s) {
        System.out.println("added: " + s);
        taskList.add(new Task(s));
    }

    static String completed(int i) {
        Task t = taskList.get(i - 1);
        t.setDone();
        return "Nice! I've marked this task as done: \n"
                + "  " + t;
    }
}

class Task {
    protected static int total;
    protected int idx;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        total++;
        this.idx = total;
        this.description = description;
        this.isDone = false;
    }

    public int getIdx() {
        return this.idx;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
