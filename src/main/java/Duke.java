import java.util.Scanner;

import java.util.ArrayList;

public class Duke {
    //variables
    public static ArrayList<String> storedStrings = new ArrayList<>();

    //main
    public static void main(String[] args) {
        Duke.greet();
        readInput();
    }

    //implementation methods
    public static void readInput(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if(input.equals("bye")){
                Duke.exit();
                break;
            } else if(input.equals("list")){
                for(int i = 1; i <= Duke.storedStrings.size(); i++){
                    System.out.print(i + ". ");
                    System.out.println(Duke.storedStrings.get(i-1));
                }
            } else{
                Duke.storedStrings.add(input);
                System.out.println("added: " + input);
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

    /**
     * Adds input to list
     *
     * @param input string you want to add to list
     */
    public static void addToList(String input){

    }
}
