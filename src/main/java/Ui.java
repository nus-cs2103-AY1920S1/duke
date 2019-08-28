import java.util.Scanner;

public class Ui {
    Scanner input;
    Parser parser;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public void showError(DukeException e) {
        printOutput(e.getMessage());
    }

    public void printOutput(String output) {
        String line = "    ____________________________________________________________\n";

        // Indent and process output line
        output = "      " + output.replaceAll("\n","\n      ") + '\n';

        System.out.println(line + output + line);
    }

    public void greetHello() {
        printOutput("Hello I'm Duke\nWhat can I do for you?");
    }

    public void greetBye() {
        printOutput("Bye. Hope to see you again soon!");
    }

    public TaskList runInputLoop(TaskList taskList) {
        this.parser = new Parser(taskList);
        String input;

        // Run input loop
        while (!(input = this.input.nextLine()).equals("bye")) {

            try {
                String output = parser.parse(input);
                printOutput(output);
            } catch (DukeException e) {
                printOutput(e.getMessage());
            }
        }

        return taskList;
    }
}
