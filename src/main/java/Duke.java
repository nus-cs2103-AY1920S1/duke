import java.util.Scanner;

public class Duke {
    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello! I'm Duke\n" + "What can I do for you?";
        String farewell = "Bye. Hope to see you again soon!";

        Scanner sc = new Scanner(System.in);

        // start interaction
        this.handleOutput(greetings);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            switch (command) {
                case "bye": this.handleOutput(farewell);
                return;
                default: this.handleOutput(command);
            }
        }
        sc.close();
    }

    private void handleOutput(String inputString) {
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println(inputString);
        System.out.println(divider);
        System.out.println();
    }
}
