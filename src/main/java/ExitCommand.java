public class ExitCommand extends Command {

    public ExitCommand(){
        this.commandType = CommandType.EXIT;
    }

    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}

