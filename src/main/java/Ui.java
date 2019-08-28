import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner scanner;
    Ui(){
        scanner = new Scanner(System.in);
    }


    /**
     * showLogo
     */
    void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * print line divider
     */
    void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * read the line command
     * @return the command type in by the user
     */
    String readCommand() {
        return scanner.nextLine();
    }

    /**
     * print a string
     * @param string
     */
    public void printString(String string) {
        System.out.println("    " + string);
    }

    /**
     * Show error message
     */

    void showLoadingError() {
        System.out.println("    Failed to load file");
    }

    void printList(ArrayList<Task> list) {
        String string = "Here are the matching tasks in your list:\n";
        for (int i = 0 ; i < list.size() ; i++ ) {
            string = string + "    " + (i + 1) + ". " + list.get(i).getTaskDetails() + "\n";
        }
        System.out.println(string);
    }

    /**
     * Show error message
     * @param string
     */
    void showError(String string){
        printString(string);
    }
}
