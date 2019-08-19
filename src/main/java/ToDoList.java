import java.util.Scanner;

public class ToDoList {
    public void run() {
        Scanner sc = new Scanner(System.in);
        String border = "    ____________________________________________________________";
        Task[] arr = new Task[100];
        int counter = 0;

        String input = sc.nextLine();
        while (!input.equals("bye")){ //first check for bye command
            if(input.equals("list")){ //check if list command
                System.out.println(border);
                for (int i = 0; i < counter; i++){
                    System.out.println("     " + (i + 1) + ".[" + arr[i].getStatusIcon() + "] " + arr[i].getDescription());
                }
                System.out.println(border);
            } else {
                String[] temp = input.split(" ");
                if (temp[0].equals("done")){ //command to add task as done
                    int done = Integer.parseInt(temp[1]) - 1;
                    arr[done].markAsDone();
                    System.out.println("     " + done + ".[" + arr[done].getStatusIcon() + "] " + arr[done].getDescription());

                } else { //command to add task to list
                    System.out.println(border + "\n" + "     added: " + input + "\n" + border);
                    arr[counter] = new Task(input);
                    counter ++;
                }
            }
            input = sc.nextLine();
        }
        if (input.equals("bye")){
            System.out.println(border + "\n" + "     Bye. Hope to see you again soon!" + "\n" +border);
        }
    }
}
