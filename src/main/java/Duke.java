import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void Duke() {
    }

    private void run() {
        String input, response;
        boolean canEnd;

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        canEnd = false;
        while (!canEnd) {
            input = myScanner.nextLine();
            if (input.equals("bye")) {
                response = "Bye. Hope to see you again soon!";
                canEnd = true;
            } else {
                response = input;
            }
            System.out.println(response);
        }
    }
}
