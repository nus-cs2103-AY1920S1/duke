import java.util.Scanner;

public class Ui {

    public String readCommand() {
        Scanner scn = new Scanner(System.in);
        String command = scn.nextLine();
        return command;
    }

    public void showLine(){
        System.out.println("_________________________________________________________________________________________");
    }

    public void showLoadingError(){
        System.out.println("No previous sessions detected. Please create a file called 'tasks.txt' under data folder.");
    }

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public void showClosing() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
