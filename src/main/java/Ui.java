import java.util.List;
import java.util.Scanner;

public class Ui{

    private void printIndentedMsg(String msg){
        String indent= "     ";
        System.out.println(indent + msg);
    }

    private void printLine(){
        String line= "    ____________________________________________________________";
        System.out.println(line);
    }

    public void messageUser(String... messages){
        printLine();
        for(String message : messages) {
            printIndentedMsg(message);
        }
        printLine();
    }

    public void messageUser(List<String> messages){
        printLine();
        for(String message : messages){
            printIndentedMsg(message);
        }
        printLine();
    }


    public String readMessage(Scanner scanner){
        return scanner.nextLine();
    }

    public void printWelcomeMessage(){
        printLine();
        printIndentedMsg("Hello! I'm Duke");
        printIndentedMsg("What can I do for you?");
        printLine();
    }

    public void printGoodbyeMessage(){

    }
}
