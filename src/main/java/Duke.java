import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private String LINES = "\n" + "____________________________________________________________" + "\n";

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    private void start() {
        System.out.println(display("Hello! I'm Duke\nWhat can I do for you?"));
        run();
    }

    private void run() {
        String s = sc.nextLine();
        while(!s.equals("bye")) {
            System.out.println(display(s));
            s = sc.nextLine();
        }
        exit();
    }

    private void exit() {
        System.out.println(display("Bye. Hope to see you again soon!"));
    }

    private String display(String text) {
        return LINES + text + LINES;
    }
}
