import java.util.Scanner;

public class Duke {
    static Scanner sc;
    
    private static void levelOne() {
        boolean exit = false;
        String exitMessage = "Bye. Hope to see you again soon!";
        while (!exit) {
            String input = sc.next();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(input);
            }
        }
        System.out.println(exitMessage);
    }
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        sc = new Scanner(System.in);
        Duke.levelOne();
        sc.close();
    }
}
