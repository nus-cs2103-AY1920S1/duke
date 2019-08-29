import java.util.Scanner;

public class Ui {

    final String lineSpace = "_______________________________\n";
    private Scanner sc;

    public Ui(){
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String startMessage = lineSpace + "Hello! I'm Duke\nWhat can I do for you?\n" + lineSpace;
        System.out.println("Hello from\n" + logo + startMessage);
    }

    public void showGoodbye(){
        String endMessage = "Bye. Hope to see you again!";
        System.out.println(endMessage);
    }

    public void showLine(){
        System.out.print(lineSpace);
    }

    public void showError(String error){
        System.out.println(error);
    }

    public String readCommand(){
        String userCmd = sc.next();
        return userCmd;
    }

    public String readDesc(){
        String desc = sc.nextLine();
        return desc;
    }

    public int readIndex(){
        int index = sc.nextInt();
        return index;
    }
}
