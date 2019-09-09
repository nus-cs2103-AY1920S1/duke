package Exceptions;

import Data.Formatter;
import UI.MessageGenerator;

public abstract class DukeException extends Exception {

    MessageGenerator msg = new MessageGenerator();
    String taskType;

    /**
     * Creates Duke Exception for exceptions in programs.
     */
    DukeException() { }

    /**
     * Creates DukeException that are related to tasks.
     *
     * @param taskType String taken in for error printing purposes.
     */
    DukeException(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Creates String for error message.
     *
     * @return error message.
     */
    public abstract String getErrorMessage();

    /**
     * Prints formatted error message.
     */
  /*  public void printError() {
        formatter.printFormat(getErrorMessage());
    } */
}
