import java.util.Scanner;

public class Duke {

    private static Scanner sc;
    private static Task[] tasks = new Task[100];
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
        } else if (input.startsWith("done")) {
            evaluateDone(input);
        } else {
            dukeOutput("added: " + input);
            addTask(input);
        }
    }

    public static void evaluateDone(String input) {
        String number = input.substring(5, input.length());
        int taskNumber = Integer.parseInt(number);
        if (number.isEmpty()) {
            dukeOutput("Invalid input!");
        } else if (taskNumber > numberOfTasks) {
            dukeOutput("Task doesn't exist");
        } else {
            tasks[taskNumber - 1].markAsDone();
            String output = "Nice! I've marked this task as done:"
                    + "\n  " + tasks[taskNumber - 1].toString();
            dukeOutput(output);
        }
    }

    public static void addTask(String description) {
        tasks[numberOfTasks++] = new Task(description);
    }

    public static void printTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numberOfTasks; i++) {
            output.append((i + 1) + ". " + tasks[i].toString());
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
