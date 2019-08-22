import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> storage = new ArrayList<String>();
        String in;
inputloop:
        while(true){
            in = sc.nextLine().trim();
            switch(in){
                case "bye":
                    System.out.println("Bye!");
                    break inputloop;

                case "list":
                    for(int i = 1; i <= storage.size(); i++) {
                        System.out.println(i + ". " + in);
                    }
                    break;

                default:
                    System.out.println("added: " + in);
                    storage.add(in);
            }
        }
    }
}
