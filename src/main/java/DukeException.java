public abstract class DukeException extends Exception {
    Formatter formatter = new Formatter();
    ErrorType type;
    String message = "☹ OOPS!!! ";
    String taskType;

    public DukeException() { }

    public DukeException(String taskType) {
        this.taskType = taskType;
    }

    public abstract String errorMessage();

    public void printError() {
        formatter.printFormat(errorMessage());
    }
}
