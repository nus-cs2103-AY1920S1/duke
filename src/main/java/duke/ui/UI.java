package duke.ui;
import java.util.Scanner;

public class UI {
    private Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println("Error in loading file!");
    }

    public void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public String getInput() {
        return sc.nextLine().trim();
    }

    public void errorUCDE() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void errorETDDE() {
        System.out.println("☹ OOPS!!! I'm sorry, the description cannot be empty :-(");
    }

    public void errorEDDDE() {
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
    }

    public void errorEEDDE() {
        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
    }

    public void errorNDDE() {
        System.out.println("☹ OOPS!!! You need to provide a date, with / to indicate it:-(");
    }
        
    public void errorITIDE() {
        System.out.println("☹ OOPS!!! You need to provide a valid task number :-(");
    }

    public void errorNFE() {
        System.out.println("☹ OOPS!!! You need to provide a valid number :-(");
    }
    public void errorDE(String errorMsg) {
        System.out.println("☹ OOPS!!! Something went wrong! " + errorMsg);
    }
    public void errorPE() {
        System.out.println("☹ OOPS!!! There was a parsing error!");
    }
}


