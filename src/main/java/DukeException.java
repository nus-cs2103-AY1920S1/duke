public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

class EmptyFieldDukeException extends DukeException {
    public EmptyFieldDukeException(String attrib, String task) {
        super("\u2639 OOPS!!! The " + attrib + " of a" +
                (task.startsWith("a|e|i|o|u") ? "n " + task : ' ' + task) + " cannot be empty.");
    }
}

class InvalidCommandDukeException extends DukeException {
    public InvalidCommandDukeException() {
        super("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

class IndexOutOfBoundsDukeException extends DukeException {
    public IndexOutOfBoundsDukeException() {
        super("Index given is out of bound.\nUse from 1 to last index of list only.");
    }
}

class IndexFormatDukeException extends DukeException {
    public IndexFormatDukeException() {
        super("List index not provided or in wrong format.");
    }
}

class DateTimeParseDukeException extends DukeException {
    public DateTimeParseDukeException() {
        super("DateTime cannot be resolved.\nExpected formats:\n2/12/2019 1800\nT1800\n2 Dec 2019\ndecember2T1300\nAnd other non-ambiguous derivatives.\nLeftmost is taken as day unless it is non-numerical month.\nNon-provided datetime defaults to current system relative.");
    }
}

class LoadFileFailDukeException extends DukeException {
    public LoadFileFailDukeException(String filePath) {
        super("Oops... " +
                filePath +
                " cannot be loaded.\nIt will be automatically overwritten once you have a list.");
    }
}

class WriteFileFailDukeException extends DukeException {
    public WriteFileFailDukeException() {
        super("Oops... Something went wrong with file writing.\"\nPlease ");
    }
}