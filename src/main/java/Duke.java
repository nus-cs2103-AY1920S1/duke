import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //dukeSayHello();
        greetings();

        //level 1
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (!input.equals("bye")) {
                System.out.println(input);
            }
            else {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }

    private static void greetings() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void dukeSayHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
