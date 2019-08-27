public class NullCommand extends Command{

    public NullCommand(){
        this.commandType = CommandType.NULL;
    }

    @Override
    public String toString() {
        return "Hello just for fun";
    }
}
