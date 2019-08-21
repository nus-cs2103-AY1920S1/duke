import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm \n" + logo + "What can I do for you?\n");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.strip().toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (input.equals("list")) {
                Task.printList();
            } else {
                Task task = new Task(input);
                System.out.println("added: " + task);
            }
        }

        sc.close();
    }
}
