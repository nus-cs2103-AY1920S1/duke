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
        while(true){
            String command = sc.nextLine();
            if(command.equals("bye")){
                System.out.println(line + "\n" + "Bye. Hope to see you again soon!" + "\n" + line);
                break;
            }else{
                System.out.println(line + "\n" + command + "\n" + line);
            }
        }
    }
}
