import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\n" + logo);
        System.out.println("Hello! I'm Duke\n What can I do for you?\n" + line);
        String input = sc.next();
        while (!input.equals("bye")) {
            System.out.println(line + "\n" + input + "\n" + line);
            input = sc.next();
        }
        System.out.println(line + "\n Bye. Hope to see you again soon! \n" + line);
    }
}
