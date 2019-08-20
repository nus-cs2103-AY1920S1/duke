public class DukeTodoException extends DukeException{

    public DukeTodoException () {
        super();
        CmdInterface.printHBars("☹ OOPS!!! The description of a todo cannot be empty.");
    }

}
