package seedu.duke.parser;

/**
 * Parser class helps to parse the user string.
 */
public class Parser {

    /**
     * Default constructor of the Parser class.
     */
    public Parser (){
    }

    /**
     * Returns the first word of the string fullCommand.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return First word of a string, delimited by whitespaces, " ".
     */
    public static String parseCommand(String fullCommand){
        return ( fullCommand.split(" ")[0] );
    }

    /**
     * Returns the todo task description of a Todo object.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Substring from index 5 (whitespace after "todo") to end of string.
     */
    public static String getTodoDescription(String fullCommand){
        return ( fullCommand.substring(5) );
    }

    /**
     * Returns the deadline task description of a Deadline string.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Substring from index 9 (whitespace after "deadline") to the '/' char inside "/by".
     */
    public static String getDeadlineDescription(String fullCommand){
        return ( fullCommand.substring(9, fullCommand.indexOf('/')) );
    }

    /**
     * Returns the unparsed DateTime string of a Deadline string.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Substring from 4 chars after '/' in "/by".
     */
    public static String getDeadlineDateTime(String fullCommand){
        return ( fullCommand.substring(4 + fullCommand.indexOf('/')) );
    }

    /**
     * Returns the event task description of an Event string.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Substring from index 6 (whitespace after "event") to the '/' char inside "/at"
     */
    public static String getEventDescription(String fullCommand){
        return ( fullCommand.substring(6, fullCommand.lastIndexOf('/')) );
    }

    /**
     * Returns the event location of an Event string.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Substring from 4 chars after '/' in "/at"
     */
    public static String getEventLocation(String fullCommand){
        return ( fullCommand.substring(4 + fullCommand.lastIndexOf('/')) );
    }

    /**
     * Returns the specified task number for a "done" string command.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Int of the substring from index 5 ( whitespace after "done" string).
     */
    public static int getFinishedTaskNum(String fullCommand){
        return ( Integer.parseInt(fullCommand.substring(5)) ) ;
    }

    /**
     * Returns the specified task number for a "delete" string command.
     * @param fullCommand Input of the user, read from Scanner object.
     * @return Int of the substring from index 7 ( whitespace after "delete" string).
     */
    public static int getDeletedTaskNum(String fullCommand){
        return ( Integer.parseInt(fullCommand.substring(7)) );
    }

}
