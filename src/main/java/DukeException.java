public class DukeException extends Exception {
    Formatter formatter = new Formatter();
    ErrorType type;
    String message = "";
    String taskType = "";

    public DukeException(ErrorType type) { this.type = type;}

    public DukeException(ErrorType type, String taskType) {
        this.type = type;
        this.taskType = taskType;
    }

    public String errorMessage() {
        message += "☹ OOPS!!! ";
        switch (type) {
            case INVALID:
                message += "I'm sorry, but I don't know what that means :-(";
                break;
            case MISSING:
                message += "The description of a ";
                switch (taskType) {
                    case "E":
                        message += "event";
                        break;
                    case "D":
                        message += "deadline";
                        break;
                    case "T":
                        message += "todo";
                        break;
                }
                message += " cannot be empty.";
        }

        return message;
    }

    public void printError() {
        formatter.printLine();
        System.err.println(formatter.format(errorMessage()));
        formatter.printLine();
    }
}
