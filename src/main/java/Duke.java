import java.util.Scanner;

public class Duke {

    private static Scanner sc;
    private static String[] tasks = new String[100];
    private static int numberOfTasks = 0;

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        dukeOutput("Hello, this is Duke.\n" + "How may I help you?");
        readInputs();
    }

    public static void readInputs() {
        sc = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("bye") && sc.hasNextLine()) {
            input = sc.nextLine();
            evaluateInput(input);
        }
        sc.close();
    }

    public static void evaluateInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            dukeOutput("Bye. Have a nice day!");
        } else if (input.equalsIgnoreCase("list")) {
            printTasks();
        }
        else {
            dukeOutput("added: " + input);
            addTask(input);
        }
    }

    public static void addTask(String task) {
        tasks[numberOfTasks++] = task;
    }

    public static void printTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numberOfTasks; i++) {
            output.append((i + 1) + ". " + tasks[i]);
            if (i != numberOfTasks - 1) {
                output.append("\n");
            }
        }
        dukeOutput(output.toString());
    }

    public static void dukeOutput(String out) {
        String bound = "_______________________________________";
        String newOutput = out.replace("\n", "\n    ");
        System.out.println("    " + bound + "\n    "
                + newOutput + "\n    " + bound);
    }

}
