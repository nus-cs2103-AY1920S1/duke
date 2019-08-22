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
                    Duke.completed(Integer.parseInt(s[1]));
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
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            ps.println((i+1) + "." + t);
        }
    }

    static void echo(String s) {
        System.out.println("added: " + s);
        taskList.add(new Task(s));
    }

    static void completed(int i) throws Exception {
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");
        Task t = taskList.get(i - 1);
        t.setDone();
        ps.println("Nice! I've marked this task as done: ");
        ps.println("  " + t);
    }
}