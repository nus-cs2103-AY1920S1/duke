import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________";
        System.out.println(line + "\n" + "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n" + line);

        Scanner sc = new Scanner(System.in);

        ArrayList<Task>allcoms = new ArrayList<Task>();
        while(true){
            String command = sc.nextLine();
            String[]words = command.split(" ");
            if(command.equals("bye")){
                System.out.println(line + "\n" + "Bye. Hope to see you again soon!" + "\n" + line);
                break;
            }else if((words.length==2)&&(words[0].equals("done"))&&(isNumeric(words[1]))){
                try {
                    int val = Integer.parseInt(words[1]);
                    allcoms.get(val - 1).taskDone();
                    System.out.println(line + "\n" + "Nice! I've marked this task as done: \n" +
                            allcoms.get(val - 1).printer() + "\n" + line);
                }catch(Exception e){
                    System.out.println("Error");
                }
            }else if(command.equals("list")){
                System.out.println(line);
                for(int i=1; i<=allcoms.size(); i++){
                    System.out.println(i + ". " + allcoms.get(i-1).printer());
                }
                System.out.println(line);
            }else{
                String[]splitwords = command.split(" ");
                if(splitwords[0].equals("todo")){
                    String midcommand = command.substring(5);
                    allcoms.add(new ToDo(midcommand));
                }else if(splitwords[0].equals("deadline")){
                    String midcommand = command.substring(9);
                    allcoms.add(new Deadline(midcommand));
                }else if(splitwords[0].equals("event")){
                    String midcommand = command.substring(6);
                    allcoms.add(new Event(midcommand));
                }else{System.err.println("You have entered an invalid command");}


                /*allcoms.add(new Task(command));*/
                System.out.println(line + "\n" + "Got it. I've added this task:" + "\n" +
                                            allcoms.get(allcoms.size()-1).printer() + "\n" + "Now you have "
                                                + allcoms.size() + " tasks in the list."+ "\n" + line);
            }
        }
    }
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
