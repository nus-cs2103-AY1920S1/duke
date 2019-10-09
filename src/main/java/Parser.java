public class Parser {

    public Parser(){
    }

    public InputType parse(String fullCommand) {
        String[] words = fullCommand.split(" ");
        switch (words[0]) {
        case ("bye") :
            return InputType.BYE;

        case ("list") :
            return InputType.LIST;

        case ("todo") :
            return InputType.TODO;

        case ("event") :
            return InputType.EVENT;

        case ("deadline") :
            return InputType.DEADLINE;

        case ("done") :
            return InputType.DONE;

        case ("delete") :
            return InputType.DELETE;

        case ("find") :
            return InputType.FIND;

        default :
            return InputType.ERROR;
        }
    }
}
