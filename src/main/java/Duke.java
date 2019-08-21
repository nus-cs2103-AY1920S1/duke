import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String[] array;
    static int count;
    static String line = "____________________________________________________________";

    public static void main(String[] args) {
        intro();
        array = new String[100];
        count = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.toLowerCase().equals("bye")) {
                System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);
                break;
            }
            addTask(s);
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

    public static void addTask(String s) {
        if (s.toLowerCase().equals("list")) {
            System.out.println(line);
            for (int i = 1; i <= count; i++) {
                System.out.println(" " + i + ". " + array[i - 1]);
            }
            System.out.println(line);
        } else {
            array[count] = s;
            count += 1;
            System.out.println(line + "\n added: " + s + "\n" + line);
        }
    }
}
