import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    _____________________________________");
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println("    _____________________________________");
        Scanner sc = new Scanner(System.in);
        String command;
        while(!( command = sc.nextLine()).equals("bye")) {
            System.out.println("    _____________________________________");
            System.out.println("    " + command);
            System.out.println("    _____________________________________");
        }
        System.out.println("    _____________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _____________________________________");
    }
}
