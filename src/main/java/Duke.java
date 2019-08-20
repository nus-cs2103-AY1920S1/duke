import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String message;
    public static String[] myList = new String[100];
    public static int idx = 0;
    public static String upperLine = "____________________________________________________________\n";
    public static String lowerLine = "____________________________________________________________\n";

    public static void addFeature(String cmd){
        myList[idx] = cmd;
        idx++;
        message = upperLine + "add: " + cmd + "\n" + lowerLine;
        System.out.println(message);
    }
    public static void doneFeature(){

    }
    public static void listFeature(){
        System.out.print(upperLine);
        for(int i = 0; i < idx; i++){
            message = myList[i];
            System.out.println((i+1) + ": " + message);
        }
        System.out.println(lowerLine);
    }

    public static void byeFeature(){
        message = upperLine + "Bye. Hope to see you again soon!\n" + lowerLine;
        System.out.println(message);
    }
    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\n"
        + "What can I do for you?\n";
        greet = upperLine + greet + lowerLine;
        System.out.println(greet);

        while(true){
            String cmd = sc.nextLine();
            if(cmd.equals("bye")){
                byeFeature();
                break;
            }else{
                if(!cmd.equals("list")){
                    addFeature(cmd);
                }else{
                    listFeature();
                }
            }
        }
    }
}