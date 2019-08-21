import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        String greet = "Hello! I'm Duke\n" +
                "What can I do for you?";
        String Exit = "Bye. Hope to see you again soon!";
        System.out.println(greet);
        String command = sc.next();
        Texts texts = new Texts();
        while(!command.equals("bye")) {
            if (!command.equals("list")) {
                texts.add(command);
                System.out.println(command);
                command = sc.next();

            } else {
                texts.print();
                command = sc.next();
            }

        }
        System.out.println(Exit);
    }

}
