import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private ArrayList<String> textEntered;

    public Duke(){
        textEntered = new ArrayList<String>(100);
    }

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
        String input = scanner.nextLine();
        while(!input.equalsIgnoreCase("Bye")){
            if(input.equalsIgnoreCase("list")) {
                printRecord();
            } else{
                addToRecord(input);
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    private void addToRecord(String recordEntry){
        this.textEntered.add(recordEntry);
        System.out.println("added: " + recordEntry + "\n");
    }

    private void printRecord(){
        for(int i = 0; i < textEntered.size(); i++){
            System.out.print((i + 1) + ". " + textEntered.get(i) + "\n");
        }
        System.out.println();
    }
}
