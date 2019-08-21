import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Scanner sc = new Scanner(System.in);
        String greet = "Hello! I'm Duke\n" +
                "What can I do for you?";
        String Exit = "Bye. Hope to see you again soon!";
        System.out.println(greet);
        String command = sc.next();
        while(!command.equals("bye")) {
            System.out.println(command);
            command = sc.next();
        }
        System.out.println(Exit);
    }
}
