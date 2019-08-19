import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String border = "____________________________________________________________";
        System.out.println(border + "\nHello! I'm Duke\nWhat can I do for you?\n" + border);

        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.next();
            if(input.equals("bye")){
                System.out.println(border + "\nBye. Hope to see you again soon!\n" + border);
            }else{
                System.out.println(border + "\n" + input + "\n" + border);
            }

        }


    }
}
