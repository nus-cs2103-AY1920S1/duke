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
            case COMMAND:
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
                break;
            case INVALIDITEM:
                message += "This item does not exist on the list";
                break;
        }
        return message;
    }

    public void printError() {
        formatter.printFormat(errorMessage());
    }
}
