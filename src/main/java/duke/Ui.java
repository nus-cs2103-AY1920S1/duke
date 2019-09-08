package duke;

import java.util.Scanner;

public class Ui {
    private boolean isEnabled;
    private boolean errorLogsEnabled;
    private Scanner scanner;
    private StringBuilder outputBuffer = new StringBuilder();

    /**
     * Creates a Ui object that controls I/O for user.
     */
    public Ui() {
        isEnabled = false;
        errorLogsEnabled = true;
        scanner = new Scanner(System.in);
    }

    /**
     * Read line using private scanner.
     * @return line input by user
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Prints greeting.
     */
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

    /**
     * Prints help.
     */
    public void printHelp() {
        println("Here is the list of instructions: list, bye, done {index}, "
                + "delete {index}, clear, todo {description}, event {description} /at "
                + "{date}, deadline {description} /by {date}");
    }

    /**
     * Prints bye.
     */
    public void printBye() {
        println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints storage file not found error.
     */
    public void storageFileNotFoundError() {
        //println("Error: Save found not found.");
    }

    /**
     * Prints IO exception error.
     */
    public void storageIoExceptionError() {
        println("Error: Unable to load save file.");
    }

    /**
     * Prints storage invalid line error.
     */
    public void storageInvalidLineError() {
        println("Error: Save file is corrupted.");
    }

    public void storageSaveFileError() {
        println("Error: Failed to save file");
    }

    /**
     * Prints line if ui is isEnabled.
     * @param line line to be printed
     */
    public void println(String line) {
        if (isEnabled) {
            //System.out.println(line);
            outputBuffer.append(line).append('\n');
        }
    }

    /**
     * Print error if errorLogsEnabled.
     * @param e DukeException error
     */
    public void printError(DukeException e) {
        if (errorLogsEnabled) {
            outputBuffer.append(Unicode.SAD_FACE)
                    .append("OOPS!!! ")
                    .append(e.getMessage())
                    .append('\n');
        }
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * Whenever UI "prints" something it puts it into the outputBuffer (StringBuilder). This also resets the buffer.
     * @return string ui output
     */
    public String flushBuffer() {
        String result = outputBuffer.toString();
        //Set length to 0 (resetting buffer)
        outputBuffer.setLength(0);
        return result;
    }
}
