import java.util.Scanner;

public class Duke {

    private static Scanner sc;

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
        } else {
            dukeOutput(input);
        }
    }

    public static void dukeOutput(String out) {
        String bound = "_______________________________________";
        String newOutput = out.replace("\n", "\n    ");
        System.out.println("    " + bound + "\n    "
                + newOutput + "\n    " + bound);
    }

}
