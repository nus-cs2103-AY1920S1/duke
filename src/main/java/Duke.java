import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String message;
    public static String[] myList = new String[100];
    public static int idx = 0;
    public static String upperLine = "____________________________________________________________\n";
    public static String lowerLine = "____________________________________________________________\n";
    public static String frontSpace = "    ";

    public static void addFeature(String cmd){
        myList[idx] = cmd;
        idx++;
        message = frontSpace + upperLine
                + frontSpace + " added: " + cmd + "\n"
                + frontSpace + lowerLine;
        System.out.println(message);
    }

    public static void listFeature(){
        System.out.print(frontSpace + upperLine);
        for(int i = 0; i < idx; i++){
            message = myList[i];
            System.out.println(frontSpace + " " + (i+1) + ". " + message);
        }
        System.out.println(frontSpace + lowerLine);
    }

    public static void byeFeature(){
        System.out.print(frontSpace + upperLine);
        message = " Bye. Hope to see you again soon!\n";
        System.out.print(frontSpace + message);
        System.out.println(frontSpace + lowerLine);
    }
    public static void main(String[] args) {
        String greet = "     Hello! I'm Duke\n"
                + "     What can I do for you?\n";
        greet = frontSpace + upperLine + greet + frontSpace + lowerLine;
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