package exceptions;

import ui.MessageGenerator;

public abstract class DukeException extends Exception {

    MessageGenerator msg = new MessageGenerator();
    String errorMessage = "OOPS!!! ";

    /**
     * Creates Duke Exception for exceptions in programs.
     */
    DukeException() {

    }

    /**
     * Creates DukeException that are related to tasks.
     *
     * @param errorMessage takes in error message for printing.
     */
    DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Creates String for error message.
     *
     * @return error message.
     */
    public abstract String getErrorMessage();

}
