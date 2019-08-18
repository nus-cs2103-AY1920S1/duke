import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private String LINES = "\n" + "____________________________________________________________" + "\n";
    private ArrayList list = new ArrayList<String>();

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
            if (s.equals("list")) {
                System.out.print(LINES);
                for (int i = 0; i < list.size() - 1; i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                System.out.print(list.size() + ". " + list.get(list.size() - 1));
                System.out.println(LINES);
            } else {
                System.out.println(display("added: " + s));
                list.add(s);
            }
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
