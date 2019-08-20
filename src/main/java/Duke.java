import java.util.Scanner;

public class Duke {
    static String line = "____________________________________________________________";

    public static void main(String[] args) {
        intro();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.toLowerCase().equals("bye")) {
                System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);
                break;
            }
            respond(s);
        }
    }

    public static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\n" + logo + " Hello! I'm Duke\n What can I do for you?\n" + line);
    }

    public static void respond(String s) {
        System.out.println(line + "\n " + s + "\n" + line);
    }
}
