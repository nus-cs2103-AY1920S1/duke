import java.util.Scanner;

public class Ui {
    Scanner scanner;
    Ui(){
        scanner = new Scanner(System.in);
    }

    void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    void showLine(){
        System.out.println("    ____________________________________________________________");
    }

    String readCommand(){
        return scanner.nextLine();
    }

    public void printString(String string){
        System.out.println("    " + string);
    }

    void showLoadingError(){
        System.out.println("    Failed to load file");
    }

    void showError(String string){
        printString(string);
    }
}
