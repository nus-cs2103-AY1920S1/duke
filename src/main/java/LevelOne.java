import java.util.Scanner;

public class LevelOne {
    public void run() {
        greet(); // Initial Greeting
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // Initial Input
        while(!input.equals("bye")) {
            echo(input);
            input = sc.nextLine();
        }
        exit();
    }

    private static void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
    }

    private static void echo(String input) {
        System.out.println("    ____________________________________________________________\n" +
                String.format("     %s\n", input) +
                "    ____________________________________________________________");
    }

    private static void exit() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
