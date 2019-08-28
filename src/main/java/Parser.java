public class Parser {
    /**
     * Parser class for reading the input lines and break them down into the command types which is InputType
     */
    public Parser(){
    }

    /**
     * parse method
     * @param fullCommand the full input string from user
     * @return Input Type
     */
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
