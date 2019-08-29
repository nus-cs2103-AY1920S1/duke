import java.util.Scanner;

public class Ui {
    private static boolean isExit = false;
    private Storage storage;
    Ui(Storage storage) {
        this.storage = storage;
    }
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
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IllegalDukeFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showLine() {
        System.out.println("_________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public static void setIsExit(boolean isExit) {
        Ui.isExit = isExit;
    }

    public static boolean getIsExit() {
        return isExit;
    }

}
