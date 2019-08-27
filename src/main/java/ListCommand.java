public class ListCommand extends Command {

    public ListCommand(){
        this.commandType = CommandType.LIST;
    }

    @Override
    public String toString() {
        return "Hello just for fun";
    }
}
