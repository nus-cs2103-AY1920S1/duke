import java.util.Scanner;

public class dukeBot {
    public void start() {
        Scanner sc =  new Scanner(System.in);

        // create storage for tasks
        String[] list = new String[100];
        int cnt = 0;
        String firstWord = "added: ";


        while(true) {
            String input = sc.nextLine();
            if(input.equals("bye") || input.equals("Bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(input.equals("list")) {
                for(int i = 0; i < cnt; i++) System.out.println(list[i]);
            } else {
                if (!input.equals("")) {
                    list[cnt++] = ((cnt) + ". ").concat(input);
                    System.out.println(firstWord.concat(input));
                }
            }
        }
    }
}
