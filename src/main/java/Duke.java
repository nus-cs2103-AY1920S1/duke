import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        //dukeSayHello();
        greetings();

        //level 2
        Scanner sc = new Scanner(System.in);
        ItemsList itemsList = new ItemsList();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                itemsList.printList();
            }
            else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else {
                itemsList.addItem(input);
            }
        }
    }

    private static void greetings() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void dukeSayHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
