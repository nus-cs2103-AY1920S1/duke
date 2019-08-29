package duke;

abstract class DukeException extends Exception {
    Formatter formatter = new Formatter();
    String message = "☹ OOPS!!! ";
    String taskType;

    /**
     * Creates Duke Exception for exceptions in programs.
     */
    DukeException() { }

    /**
     * Creates DukeException that are related to tasks.
     * @param taskType String taken in for error printing purposes.
     */
    DukeException(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Creates String for error message.
     * @return error message.
     */
    abstract String errorMessage();

    /**
     * Prints formatted error message.
     */
    void printError() {
        formatter.printFormat(errorMessage());
    }
}
