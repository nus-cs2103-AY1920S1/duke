package Utilities;

import Command.*;

/**
 * Utilities.Parser processes the user commands and carries out the specific functions on it
 */
public class Parser {


    /**
     * Class Constructor
     */
    public Parser(){}

    /**
     * Interprets the command and calls the executing class
     * @param command
     * @return the command as an object
     */
    public static Command parse(String command){
        String[]words = command.split(" ");
        if (command.equals("bye")) {                                                                                    //IF BYE
            return new ByeCommand(command);
        } else if ( (words.length==2) && (words[0].equals("done")) && (isNumeric(words[1])) ) {                         //IF DONE
            return new DoneCommand(command);
        } else if ((words.length==2)&&(words[0].equals("delete"))&&(isNumeric(words[1]))){                              //IF DELETE
            return new DeleteCommand(command);
        } else if (command.equals("list")){
            return new ListCommand(command);
        } else if(words[0].equals("find")){
            return new FindCommand(command);
        } else {
            return new TaskCommand(command);
        }

    }

    /**
     * determines whether parameter is an integer
     * @param str takes in the string that will be checked
     * @return boolean value
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
