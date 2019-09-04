package duke.ui;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the UI that the user will be interacting with.
 */
public class Ui{

    /**
     * Prints an indented message.
     *
     * @param msg Message to be printed
     */
    private void printIndentedMsg(String msg){
        String indent= "     ";
        System.out.println(indent + msg);
    }

    /**
     * Prints a line in the UI.
     */
    private void printLine(){
        String line= "    ____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Prints a variable number of messages, using varargs to accept different numbers of messages.
     *
     * @param messages The String[] of messages to be printed
     */
    public void messageUser(String... messages){
        printLine();
        for(String message : messages) {
            printIndentedMsg(message);
        }
        printLine();
    }

    /**
     * Prints messages from a {@literal List<String>} of messages.
     *
     * @param messages The messages to be printed
     */
    public void messageUser(List<String> messages){
        printLine();
        for(String message : messages){
            printIndentedMsg(message);
        }
        printLine();
    }


    /**
     * Reads the next message and returns it as a String.
     *
     * @param scanner the scanner to use
     * @return the read line as a String
     */
    public String readMessage(Scanner scanner){
        return scanner.nextLine();
    }

    /**
     * Prints a welcome message as defined in the CS2103T website.
     */
    public void printWelcomeMessage(){
        printLine();
        printIndentedMsg("Hello! I'm Duke");
        printIndentedMsg("What can I do for you?");
        printLine();
    }

}
