import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<String> list;
    public Duke(){}
    private void Greet(){
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    private void Echo(String a){
        System.out.println(a);
    }
    private void Exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke d = new Duke();
        Scanner scan = new Scanner(System.in);
        d.Greet();
        d.list = new ArrayList<>();
        while(scan.hasNextLine()){
            String a = scan.nextLine();
            if(a.equals("bye")){
                d.Exit();
                break;
            }
            else if(a.equals("list")){
                for(int i = 0; i < d.list.size(); i+=1){
                    int j = i+1;
                    System.out.println(j + ". " + d.list.get(i));
                }
            }
            else{
               d.list.add(a);
               System.out.println("added: " + a);
            }

        }

    }
}
