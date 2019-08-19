import java.util.Scanner;

public class ToDoList {
    public void run() {
        Scanner sc = new Scanner(System.in);
        String border = "    ____________________________________________________________";
        String[] arr = new String[100];
        int counter = 0;

        String input = sc.nextLine();
        while (!input.equals("bye")){
            if(input.equals("list")){
                System.out.println(border);
                for (int i = 0; i < counter; i++){
                    System.out.println("     " + (i + 1) + ". " + arr[i]);
                }
                System.out.println(border);
            } else {
                System.out.println(border + "\n" + "     added: " + input + "\n" + border);
                arr[counter] = input;
                counter ++;
            }
            input = sc.nextLine();
        }
        if (input.equals("bye")){
            System.out.println(border + "\n" + "     Bye. Hope to see you again soon!" + "\n" +border);
        }
    }
}
