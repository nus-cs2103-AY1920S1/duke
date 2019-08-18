import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private boolean running;

    public Duke() {
        scanner = new Scanner(System.in);
        running = true;
    }

    public void start() {
        String input;
        say("Hello! I'm Duke\nWhat can I do for you?");
        while (running) {
            input = scanner.next();
            if (input.equals("bye")) {
                exit();
            } else {
                say(input);
            }
        }
    }

    private void exit() {
        this.running = false;
        say("Bye. Hope to see you again soon!");
    }

    private void say(String sequence) {
        String indent = sequence.replaceAll("(?m)^", "\t");
        System.out.printf(
                "       ____________________________________________________________\n     %s\n       ____________________________________________________________\n",
                indent);
    }
}