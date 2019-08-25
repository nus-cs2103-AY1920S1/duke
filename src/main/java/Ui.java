import java.util.Scanner;

public class Ui {

    public void showWelcomeMessage(){
        String message = "Hello! I'm Slave! Your very own productivity application.\n" +
                "     What can I do for you?";
        System.out.println(Formatter.formatMessage(message));
    }

    public void showByeMessage(){
        String message = "Bye! Thanks for using me! Will be right here when you need me.";
        System.out.println(Formatter.formatMessage(message));
    }

    public void showErrorMessage(DukeException e){
        System.out.println(Formatter.formatMessage(e.getMessage()));
    }

    public String readCommand(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
