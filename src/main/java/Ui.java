import java.util.Scanner;
public class Ui {
    private boolean isRunning = true;
    private static Parser commandInterpreter = new Parser();

    /**
     * prints message when Duke is started by User.
     */
    public void sayHello(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

    }

    /**
     * receives commands from user
     */

    public void takeUserCommand(){
        sayHello();
        Scanner scan = new Scanner(System.in);
        while(isRunning){
            System.out.print("input command here: ");
            String input = scan.nextLine();
            try {
                isRunning = commandInterpreter.checkCommand(input);
            } catch (NoValidCommandException ex){
                ex.printErrorMessage();
            }
        }
    }


}
