public class DukeUnknownCommandException extends DukeException {

    public DukeUnknownCommandException () {
        super();
        CmdInterface.printHBars("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
