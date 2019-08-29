package duke.command;

import duke.excaptions.IllegalDukeArgumentException;
import duke.excaptions.IllegalDukeFormatException;

import java.util.Scanner;
/**
 *The Ui class is one of the class in command package which used to deal the interaction with users
 */
public class Ui {
    private static boolean isExit = false;
    private Storage storage;

    /**
     * The constructor to write in Storage object
     * @param storage
     */
    public Ui(Storage storage) {
        this.storage = storage;
    }

    /**
     * This method print out the welcome words
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = "Hello! I'm Duke\n" +
                "What can I do for you?";

        System.out.println(greet);
    }
    Scanner sc = new Scanner(System.in);

    /**
     * this method is to read the command line by line and pass them to Parser
     */
    public void readCommand() {
        while(sc.hasNextLine()) {
            String fullCommand = sc.nextLine();
            String[] splitCommand = fullCommand.split(" ", 2);
            try {
                Parser parser = new Parser(splitCommand);
                parser.parse(storage);
                if(isExit) {
                    break;
                }
                this.showLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IllegalDukeFormatException e) {
                System.out.println(e.getMessage());
            } catch (IllegalDukeArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * a method to print splitting lines
     */
    public void showLine() {
        System.out.println("_________________________________________________________");
    }

    /**
     * A method to print error message
     * @param message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * method to modify the isExit boolean to check if is time to exit
     * @param isExit a boolean to check if is time to exit
     */
    public static void setIsExit(boolean isExit) {
        Ui.isExit = isExit;
    }

    /**
     * method to get exit status
     * @return boolean this return isExit
     */
    public static boolean getIsExit() {
        return isExit;
    }

}
