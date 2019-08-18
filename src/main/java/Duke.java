import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke dukebot = new Duke();
        dukebot.greetUser();
        dukebot.echoUser();
    }

    private void greetUser(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    private void echoUser(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while(!input.equalsIgnoreCase("Bye")){
            System.out.println(input + "\n");
            input = scanner.next();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
