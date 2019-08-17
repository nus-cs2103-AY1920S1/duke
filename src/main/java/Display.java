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

    public void instance(){
        this.printGreeting();
    }
}
