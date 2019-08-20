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

        ArrayList<String>allcoms = new ArrayList<String>();
        while(true){
            String command = sc.nextLine();
            if(command.equals("bye")){
                System.out.println(line + "\n" + "Bye. Hope to see you again soon!" + "\n" + line);
                break;
            }else if(command.equals("list")){
                System.out.println(line);
                for(int i=1; i<=allcoms.size(); i++){
                    System.out.println(i + ". " + allcoms.get(i-1));
                }
                System.out.println(line);
            }else{
                allcoms.add(command);
                System.out.println(line + "\n" + "added: " + command + "\n" + line);
            }
        }
    }
}
