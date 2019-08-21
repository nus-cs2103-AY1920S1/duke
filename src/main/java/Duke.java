import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Task[] array;
    static int count;
    static String line = "____________________________________________________________";

    public static void main(String[] args) {
        intro();
        array = new Task[100];
        count = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            if (s.equals("done")) {
                int task = sc.nextInt() - 1;
                markAsDone(task);
                Task t = array[task];
                System.out.println(line + "\n Nice! I've marked this task as done: \n   [" + t.getStatusIcon() +
                        "] " + t.description + "\n" + line);
            } else {
                s += sc.nextLine();
                if (s.toLowerCase().equals("bye")) {
                    System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);
                    break;
                }
                process(s);
            }
        }
    }

    public static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\n" + logo + "\n Hello! I'm Duke\n What can I do for you?\n" + line);
    }

    public static void process(String s) {
        if (s.toLowerCase().equals("list")) {
            System.out.println(line);
            for (int i = 1; i <= count; i++) {
                System.out.println(" " + i + ". [" + array[i - 1].getStatusIcon() + "] " + array[i - 1].description);
            }
            System.out.println(line);
        } else {
            array[count] = new Task(s);
            count += 1;
            System.out.println(line + "\n added: " + s + "\n" + line);
        }
    }

    public static void markAsDone(int i) {
        array[i].done();
    }
}