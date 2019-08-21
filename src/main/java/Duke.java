import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String opening = String.format("%s\nHello! I'm Duke\nWhat can I do for you?", logo);
        String closing = "Bye. Hope to see you again soon!";

        System.out.println(opening);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String cmmd = sc.next();
            if (cmmd.equals("bye")) {
                System.out.println(closing);
                System.exit(0);
            }
            System.out.println(cmmd);
        }
    }
}
