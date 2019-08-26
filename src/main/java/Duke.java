import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.start();
    }

    private void start() {
        boolean notShutdown = true;

        this.greetHello();
        do {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    this.greetGoodbye();
                    notShutdown = false;
                    break;
                default:
                    this.formattedPrintln(input);
            }
        } while (notShutdown);
    }

    private void formattedPrintln(String output) {
        System.out.println("____________________________________________________________\n"
                + output
                + "\n"
                + "____________________________________________________________\n");
    }

    private void greetHello() {
        String hello = "Hello! I'm Duke\n"
                + "What can I do for you?";
        this.formattedPrintln(hello);
    }

    private void greetGoodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        this.formattedPrintln(goodbye);
    }
}