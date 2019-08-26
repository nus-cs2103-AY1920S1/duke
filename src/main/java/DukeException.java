class DukeException extends Exception {
}

class InputUnknownException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

class InvalidInputFormatException extends DukeException {
    @Override
    public String getMessage() {
        return "The format for this command is wrong. Type 'formats' to check";
    }
}

class ListItemEmptyException extends DukeException {
    @Override
    public String getMessage() {
        return "No such item in list!";
    }
}