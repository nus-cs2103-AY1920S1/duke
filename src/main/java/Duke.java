import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String word = sc.nextLine();
            System.out.println("    ____________________________________________________________");
            if (word.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else {
                System.out.println("     " + word);
            }
            System.out.println("    ____________________________________________________________");
        }
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */
    }
}
