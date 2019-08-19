import java.util.Scanner;

public class Duke {

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

        while(scan.hasNextLine()){
            String a = scan.nextLine();
            if(a.equals("bye")){
                d.Exit();
                break;
            }
            else{
                d.Echo(a);
            }

        }

    }
}
