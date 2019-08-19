import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String border = "____________________________________________________________";
        System.out.println(border + "\nHello! I'm Duke\nWhat can I do for you?\n" + border);

        Task[] userList = new Task[100];
        int counter = 0;
        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();
            if(input.equals("bye")) {
                System.out.println(border + "\nBye. Hope to see you again soon!\n" + border);
            }else if(input.equals("list")){
                int itemNumber = 1;
                System.out.println(border + "\nHere are the tasks in your list:");
                for(int i = 0; i < counter; i++) {
                    System.out.println(itemNumber + ".[" +userList[i].getStatusIcon() + "] " +
                            userList[i].getDescription());
                    itemNumber ++;
                }
                System.out.println(border);
            }else if(input.startsWith("done")) {
                String[] taskDone = input.split(" ");
                int taskIndex = Integer.parseInt(taskDone[1]);
                userList[taskIndex - 1].markAsDone();
                System.out.println(border + "\nNice! I've marked this task as done:");
                System.out.println("[" + userList[taskIndex - 1].getStatusIcon() + "] " +
                        userList[taskIndex - 1].getDescription() +"\n" + border);
            }else {
                Task t = new Task(input);
                userList[counter] = t;
                counter ++;
                System.out.println(border + "\n" + "added: " + input + "\n" + border);
            }

        }


    }
}
