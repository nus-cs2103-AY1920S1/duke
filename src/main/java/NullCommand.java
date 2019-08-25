public class NullCommand extends Command{

    public NullCommand(){
        this.commandType = CommandType.NULL;
    }

    @Override
    public void execute() {

    }
}
