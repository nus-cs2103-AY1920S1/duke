import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        Duke d = new Duke();
        d.greeting();
        d.listen();
    }

    private Duke() {}

    private void greeting() {
        System.out.println("Hello! I'm Duke");
    }

    private void listen() {
        Scanner listener = new Scanner(System.in);
        while(listener.hasNext()) {
            String line = listener.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else System.out.println(line);
            // handleInput(line);
        }
    }

    private void handleInput(String line) {
        if (line.equals("bye")) {
            // throws error
        } else {
            // echo
        }
    }
}
