/* Name: Ang Kai Qi
   MatricNo: A0190206N
 */
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.greet();
        Duke.echo();
    }

    static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    static void echo() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        while (!s.equals("bye")) {
            System.out.println(s);
            s = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
