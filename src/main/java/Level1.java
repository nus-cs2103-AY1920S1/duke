import java.util.Scanner;

public class Level1 {
    public static void main(String[] args) {
        //introduction
        startDuke();

        //input action
        Scanner s = new Scanner(System.in);
        String action = s.next();

        while (!action.equalsIgnoreCase("bye")) {
            line();
            System.out.println("\t" + action);
            line();
            action = s.next();
        }

        //ending
        endDuke();
    }

    private static void startDuke() {
        line();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        line();
    }

    private static void endDuke() {
        line();
        System.out.println("\tBye. Hope to see you again soon!");
        line();
    }

    private static void line() {
        System.out.println("\t____________________________________________________________");
    }
}
