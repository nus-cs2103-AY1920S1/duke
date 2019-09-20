class DukeException extends Exception {
}

class InputUnknownException extends DukeException {
    @Override
    public String getMessage() {
        return ">:( Grow up Tom. Can't understand your words. ";
    }
}

class InvalidInputFormatException extends DukeException {
    @Override
    public String getMessage() {
        return "The input format is wrong. Check out 'formats'";
    }
}

class ListItemEmptyException extends DukeException {
    @Override
    public String getMessage() {
        return "Nothing like that in the list!";
    }
}