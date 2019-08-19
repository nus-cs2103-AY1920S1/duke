import java.util.Scanner;

public class Duke {
    /**
     * Setup Duke.
     * @param args the setup arguments
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Run Duke.
     */
    public void run() {
        sayGreeting();
        String command;
        Scanner sc = new Scanner(System.in);
        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            printFormatted(command);
        }
        sayBye();
    }

    private void sayGreeting() {
        printFormatted("Hello! I'm Duke\nWhat can I do for you?");
    }

    private void sayBye() {
        printFormatted("Bye. Hope to see you again soon!");
    }

    private void printFormatted(String output) {
        String horLine = "\t____________________________________________________________";
        String[] lines = output.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(horLine + "\n");
        for (String line : lines) {
            stringBuilder.append("\t " + line + "\n");
        }
        stringBuilder.append(horLine + "\n");
        System.out.println(stringBuilder);
    }
}
