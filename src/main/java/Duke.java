import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String border = "____________________________________________________________";
        System.out.println(border + "\nHello! I'm Duke\nWhat can I do for you?\n" + border);

        String[] userList = new String[100];
        int counter = 0;
        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();
            if(input.equals("bye")){
                System.out.println(border + "\nBye. Hope to see you again soon!\n" + border);
            }else if(input.equals("list")){
                int itemNumber = 1;
                System.out.println(border);
                for(int i = 0; i < counter; i++){
                    System.out.println(itemNumber + ". " + userList[i]);
                    itemNumber ++;
                }
                System.out.println(border);
            }else{
                userList[counter] = input;
                counter ++;
                System.out.println(border + "\n" + "added: " + input + "\n" + border);
            }

        }


    }
}
