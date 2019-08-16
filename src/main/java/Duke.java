import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<String> list;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.runDuke();
    }

    public Duke() {
        this.list = new ArrayList<String>();
    }

    public void runDuke() {
        printGreetingMessage();

        boolean isDone = false;
        while (!isDone) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                isDone = true;
            } else if (input.equals("list")) {
                printList();
            } else {
                addToList(input);
            }
        }

        printExitMessage();
    }

    public void printGreetingMessage() {
        printMessage("Hello, I'm Duke\n\tWhat can I do for you?");
    }

    public void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public void addToList(String input) {
        list.add(input);
        printMessage("added: " + input);
    }

    public void printList() {
        printLine();
        for (int i = 0; i < list.size(); i++) {
            int oneBasedIndex = i + 1;
            System.out.printf("\t%d. %s\n", oneBasedIndex, list.get(i));
        }
        printLine();
    }

    public void printMessage(String message) {
        printLine();
        System.out.println("\t" + message);
        printLine();
    }

    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }
}