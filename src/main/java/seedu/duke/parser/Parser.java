package seedu.duke.parser;

public class Parser {
    public Parser (){

    }

    public static String parseCommand(String fullCommand){
        return ( fullCommand.split(" ")[0] );
    }

    public static String getTodoDescription(String fullCommand){
        return ( fullCommand.substring(5) );
    }

    public static String getDeadlineDescription(String fullCommand){
        return ( fullCommand.substring(9, fullCommand.indexOf('/')) );
    }

    public static String getDeadlineDateTime(String fullCommand){
        return ( fullCommand.substring(4 + fullCommand.indexOf('/')) );
    }

    public static String getEventDescription(String fullCommand){
        return ( fullCommand.substring(6, fullCommand.lastIndexOf('/')) );
    }

    public static String getEventLocation(String fullCommand){
        return ( fullCommand.substring(4 + fullCommand.lastIndexOf('/')) );
    }


}
