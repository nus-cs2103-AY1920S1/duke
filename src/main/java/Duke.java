import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);

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

    public void runDuke() {
        printGreetingMessage();

        boolean isDone = false;
        while (!isDone) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                isDone = true;
            } else {
                echoInput(input);
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

    public void echoInput(String input) {
        printMessage(input);
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