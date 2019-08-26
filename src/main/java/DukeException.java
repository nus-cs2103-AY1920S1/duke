/**
 * throws exception and prints out the message input
 */
public class DukeException extends Exception{
    public DukeException(String message){
        System.out.println(message);
    }
}