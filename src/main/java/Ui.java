package duke;

import java.util.Scanner;

public class Ui {
    public String format(String command) {
        return "    ____________________________________________________________\n"
                + indent(command)
                + "    ____________________________________________________________\n";
    }

    public String indent(String command) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(command);
        while(scanner.hasNext()) {
            String temp = scanner.nextLine();
            stringBuffer.append("     " + temp + "\n");
        }
        scanner.close();
        return stringBuffer.toString();
    }

    public void printResponse(String command) {
        System.out.println(format(command));
    }

    public String readCommand() {
        // Unable to close scanner - will trigger NullException error on scanner.nextLine()
        Scanner scanner = new Scanner(System.in);
        String fullCommand = scanner.nextLine();
        return fullCommand;
    }

    public void showWelcome() {
        this.printResponse("Hello! I'm Duke\n"
                + "What can I do for you?");
    }

    public void showError(String message) {
        this.printResponse(message);
    }

    public void showLoadingError() {
        this.printResponse("☹ OOPS!!! No tasks saved.");
    }
}