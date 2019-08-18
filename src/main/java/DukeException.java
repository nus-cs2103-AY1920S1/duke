public class DukeException extends Exception {

    public DukeException() {
    }
}

class EmptyCommandException extends DukeException {
    private String type;

    public  EmptyCommandException(String type) {
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

class UnknownCommandExeption extends DukeException {
    @Override
    public String toString() {
        return " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

class NoDateException extends DukeException {
    private String type;

    public NoDateException(String type) {
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
