class DukeException extends Exception {}

class InputUnknownException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
class EmptyDescriptionException extends DukeException {
    @Override
    public String getMessage() {
        return "What's the description of the item?";
    }
}
class EmptyListIndexException extends DukeException {
    @Override
    public String getMessage() {
        return " which one? (input an integer)";
    }
}
class ListItemEmptyException extends DukeException {
    @Override
    public String getMessage() {
        return "No such item in list!";
    }
}
class EmptyTimeDueException extends DukeException {
    @Override
    public String getMessage() {
        return "When is it due? eg. /by 2359 sunday";
    }
}