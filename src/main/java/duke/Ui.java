package duke;

import java.util.Scanner;

public class Ui {
    private boolean enabled;
    private boolean errorLogsEnabled;
    private Scanner scanner;
    public Ui() {
        enabled = false;
        errorLogsEnabled = true;
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void printGreeting() {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        println("Hello from\n" + logo);
        */
        println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void printHelp() {
        println("Here is the list of instructions: list, bye, done {index}, delete {index}, clear, todo {description}, event {description} /at {date}, deadline {description} /by {date}");
    }

    public void printBye() {
        println("Bye. Hope to see you again soon!");
    }

    public void storageFileNotFoundError() {
        //println("Error: Save found not found.");
    }

    public void storageIOExceptionError() {
        println("Error: Unable to load save file.");
    }

    public void storageInvalidLineError() {
        println("Error: Save file is corrupted.");
    }

    public void storageSaveFileError() {
        println("Error: Failed to save file");
    }

    public void println(String line) {
        if (enabled) {
            System.out.println(line);
        }
    }

    public void printError(DukeException e) {
        if (errorLogsEnabled) {
            System.out.println("☹ OOPS!!! " + e.getMessage());
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
