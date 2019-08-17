import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    
    private static void levelOne() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String exitMessage = "Bye. Hope to see you again soon!";
        while (!exit) {
            String input = sc.next();
            if (input.equals("bye")) {
                exit = true;
            } else {
                System.out.println(input);
            }
        }
        System.out.println(exitMessage);
        sc.close();
    }

    private static void levelTwo() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String exitMessage = "Bye. Hope to see you again soon!";
        List<String> l = new ArrayList<>();
        while (!exit) {
            String input = sc.nextLine();
            switch (input) {
                case "list":
                    l.forEach(task -> System.out.println(task));
                    break;
                case "bye":
                    exit = true;
                    break;
                default:
                    String task = String.format("%d. %s", l.size() + 1, input);
                    l.add(task);
                    System.out.println("added: " + input);
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
        // Duke.levelOne();
        Duke.levelTwo();
    }
}
