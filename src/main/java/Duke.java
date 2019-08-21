import java.util.Scanner;

public class Duke {
    static Task[] array;
    static int count;
    static String line = "____________________________________________________________";

    public static void main(String[] args) {
        intro();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String s = sc.next().toLowerCase();

            if (s.equals("done")) {
                markAsDone(sc.nextInt() - 1);
            }

            else if (s.equals("todo") || s.equals("deadline") || s.equals("event")) {
                process(s, sc.nextLine().trim());
            }

            else if (s.toLowerCase().equals("list")) {
                System.out.println(line);
                for (int i = 1; i <= count; i++) {
                    System.out.println(" " + i + ". " + array[i - 1].toString());
                }
                System.out.println(line);
            }

            else {
                if (s.equals("bye")) {
                    System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);
                    break;
                }
            }
        }
    }

    public static void intro() {
        array = new Task[100];
        count = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\n" + logo + "\n Hello! I'm Duke\n What can I do for you?\n" + line);
    }

    public static void process(String s, String des) {
        Task task = new Task("null");
        if (s.equals("todo")) {
            task = new ToDo(des);
        } else if (s.equals("deadline")) {
            String[] arr = des.split("/by");
            task = new Deadline(arr[0].trim(), arr[1].trim());
        } else if (s.equals("event")) {
            String[] arr = des.split("/at");
            task = new Event(arr[0].trim(), arr[1].trim());
        }
        array[count] = task;
        count += 1;
        printTask(task);
    }

    public static void printTask(Task t) {
        System.out.println(line + "\n Got it. I've added this task: \n  " +
                t.toString() + "\n Now you have " + count + " tasks in the list.\n" + line);
    }

    public static void markAsDone(int i) {
        array[i].done();
        Task t = array[i];
        System.out.println(line + "\n Nice! I've marked this task as done: \n " + t.toString() + "\n" + line);
    }
}