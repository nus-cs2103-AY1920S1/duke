import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list;

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
        this.list = new ArrayList<Task>();
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
            } else if (input.startsWith("done ")) {
                int oneBasedIndex = Integer.parseInt(input.replace("done ", ""));
                markAsDone(oneBasedIndex);
            } else {
                addTaskToList(input);
            }
        }

        printExitMessage();
    }

    public void markAsDone(int oneBasedIndex) {
        int zeroBasedIndex = oneBasedIndex - 1;
        list.get(zeroBasedIndex).markAsDone();
        printMessage("Nice! I've marked this task as done:\n\t\t" + list.get(zeroBasedIndex));
    }

    public void addTaskToList(String input) {
        list.add(new Task(input));
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

    public void printGreetingMessage() {
        printMessage("Hello, I'm Duke\n\tWhat can I do for you?");
    }

    public void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
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