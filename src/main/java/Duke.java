import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String message;
    public static Task[] myList = new Task[100];
    public static int idx = 0;
    public static String upperLine = "____________________________________________________________\n";
    public static String lowerLine = "____________________________________________________________\n";

    public static void addFeature(String cmd){

        myList[idx] = new Task(cmd);
        idx++;
        message = upperLine + "add: " + cmd + "\n" + lowerLine;
        System.out.println(message);
    }
    public static void doneFeature(String num_string){
        int i = Integer.parseInt(num_string);
        System.out.print(upperLine);
        System.out.println("Nice! I've marked this task as done: ");
        Task current = myList[i - 1];
        current.markAsDone();
        System.out.println(current);
        System.out.println(lowerLine);

    }
    public static void listFeature(){
        System.out.print(upperLine);
        String title = "Here are the tasks in your list:\n";
        System.out.print(title);
        for(int i = 0; i < idx; i++){
            System.out.println((i+1) + ". " + myList[i]);
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
                if(cmd.equals("list")){
                    listFeature();
                }else if(cmd.split(" ").length == 2 &&
                        cmd.split(" ")[0].equals("done")){
                    doneFeature(cmd.split(" ")[1]);
                }else{
                    addFeature(cmd);
                }
            }
        }
    }
}