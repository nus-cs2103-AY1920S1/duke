import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "\nWhat can I do for you?" );

        String command = promptEntry();

        while(!command.equals("bye")){
            printCommand(command);
            command = promptEntry();
        }
        printCommand("Bye. Hope to see you again soon!");

    }

    public static void printCommand(String command) {
        System.out.println(command);
    }

    public static String promptEntry() {
       return sc.nextLine();
    }

}
