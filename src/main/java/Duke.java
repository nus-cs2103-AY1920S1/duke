import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke.greet();
        readInput();
    }

    public static void readInput(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if(input.equals("bye")){
                Duke.exit();
                break;
            } else {
                System.out.println(input);
            }
        }
    }

    public static void greet(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void echo(String input){
        System.out.println(input);
    }

    public static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
