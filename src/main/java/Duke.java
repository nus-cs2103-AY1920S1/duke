import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("Hello! I'm Duke \n What can I do for you?");
        while(sc.hasNext()) {
           String next = sc.next(); 
           if(next == "bye") {
                System.out.println("Bye. Hope to see you again soon!");
                break;
           } else {
                System.out.println(next);
           }
        }
        
    }
}
