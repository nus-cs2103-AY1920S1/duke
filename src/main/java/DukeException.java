public class DukeException extends Exception {
    public DukeException(String message){
        super(message);
    }
}

class EmptyFieldDukeException extends DukeException{
    public EmptyFieldDukeException(String attrib, String task){
        super("\u2639 OOPS!!! The " + attrib + " of a" +
                (task.startsWith("a|e|i|o|u") ? "n " + task : ' ' + task) + " cannot be empty.");
    }
}

class InvalidCommandDukeException extends DukeException{
    public InvalidCommandDukeException(){
        super("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

class IndexOutOfBoundsDukeException extends DukeException{
    public IndexOutOfBoundsDukeException(){
        super("Index given is out of bound.\nUse from 1 to last index of list only.");
    }
}

class IndexFormatDukeException extends DukeException{
    public IndexFormatDukeException(){
        super("List index not provided or in wrong format.");
    }
}