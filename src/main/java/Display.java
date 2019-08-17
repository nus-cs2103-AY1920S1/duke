import java.util.Scanner;

class Display{
    private Scanner _sc;

    public Display(){
        this._sc = new Scanner(System.in);
    }

    public static void printGreeting(){
        String greeting = "Hello! I'm Duke\n"
            + "What can I do for you?\n";
        System.out.println(greeting);
    }

    public static void printExitMessage(){
        String farewell = "Bye. Hope to see you "
            + "again soon!";
        System.out.println(farewell);
    }

    public static boolean isEndCommand(String cmd){
        return cmd.toUpperCase().equals("BYE");
    }


    public void instance(){
        this.printGreeting();
        String command = this._sc.nextLine();

        while(! isEndCommand(command)){
            System.out.println("echo: " + command);

            command = this._sc.nextLine();
        }
        this.printExitMessage();
    }
}
