public class DukeException extends Exception {

    public String getMessage() {
        return toString();
    }
}

class EmptyCommandException extends DukeException {
    private String type;

    EmptyCommandException(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if (this.type.equals("event")) {
            return " ☹ OOPS!!! The description of an event cannot be empty.";
        } else {
            return " ☹ OOPS!!! The description of a " + this.type + " cannot be empty.";
        }
    }
}

class InvalidTaskException extends DukeException {
    @Override
    public String toString() {
        return " ☹ OOPS!!! I'm sorry, there is no such type of task :-(";
    }
}
class UnknownCommandExeption extends DukeException {
    @Override
    public String toString() {
        return " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

class NoDateException extends DukeException {
    private String type;

    NoDateException(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return " ☹ OOPS!!! The " + this.type + " must have a date.";
    }

}

class NoTaskException extends DukeException {
    public String toString() {
        return " ☹ OOPS!!! The task does not exist.";
    }
}

class NoPostponeException extends DukeException {
    public String toString() {
        return " ☹ OOPS!!! The task has no date and cannot be postponed.";
    }
}
