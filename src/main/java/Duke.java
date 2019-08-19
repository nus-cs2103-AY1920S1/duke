import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> allItems = new LinkedList<>();
        String line = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + line);

        // Reads in input
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(line + "\n");
            if (!input.equals("list")) {
                allItems.add(input);
                //Prints "added: Item"
                System.out.println("added: " + input);
            } else {
                // Prints "1. Item"
                allItems.forEach(x -> System.out.println(allItems.indexOf(x) + 1 + ". " + x));
            }
            System.out.println("\n" + line);
            input = sc.nextLine();

        }
        System.out.println(line + "\n Bye. Hope to see you again soon! \n" + line);
    }
}
