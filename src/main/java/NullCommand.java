import java.util.List;


public class NullCommand extends Command{

    public NullCommand(){
        this.commandType = CommandType.NULL;
    }

    @Override
    public void execute(List<Task> lst , Ui ui) throws DukeException {
        throw new DukeException("Invalid Command! Please try again.", DukeExceptionType.INVALIDCOMMAND);
    }


}
