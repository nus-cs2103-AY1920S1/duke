import java.util.Scanner;
/**
 *  Week 2 IP Deliverable
 *
 *  @author Ahmed Bahajjaj
 */
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Scanner sc = new Scanner(System.in);
        boolean hi = true;
        String input = "";
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while(hi) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                hi = false;
            } else {
                System.out.println(input);
            }
        }
        sc.close();
    }
}
