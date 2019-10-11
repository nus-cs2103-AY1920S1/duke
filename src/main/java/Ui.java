import java.util.Scanner;

public class Ui{

    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println(logo + "Hello! I'm Duke \nWhat can I do for you?");
        return logo + "Hello! I'm Duke \nWhat can I do for you?";
    }


    public String showError(String msg) {
        return msg;
    } 

    public static String  printAddedMsg() {
        return "Task added!";
    }

    public void showLoadingError() {
        System.out.println("Loading Error!");
    }

}