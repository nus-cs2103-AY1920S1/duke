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
                    System.out.println(itemNumber + "." + userList[i].toString());
                    itemNumber ++;
                }
                System.out.println(border);
            }else if(input.startsWith("done")) {
                String[] taskDone = input.split(" ");
                int taskIndex = Integer.parseInt(taskDone[1]);
                userList[taskIndex - 1].markAsDone();
                System.out.println(border + "\nNice! I've marked this task as done:");
                System.out.println(userList[taskIndex - 1].toString() +  "\n" + border);

            }else if(input.startsWith("deadline")) {
                String[] deadLineDate = input.substring(9).split(" /by ");
                String taskD = deadLineDate[0];
                String dateD = deadLineDate[1];
                Deadline newDeadLine = new Deadline(taskD, dateD);
                userList[counter] = newDeadLine;
                counter ++;
                System.out.println(border + "\nGot it. I've added this task:");
                System.out.println(newDeadLine.toString());
                System.out.println("Now you have " + counter + " tasks in the list.\n" + border);

            }else if(input.startsWith("event")) {
                String[] eventDate = input.substring(6).split(" /at ");
                String taskE = eventDate[0];
                String dateE = eventDate[1];
                Event newEvent = new Event(taskE, dateE);
                userList[counter] = newEvent;
                counter ++;
                System.out.println(border + "\nGot it. I've added this task:");
                System.out.println(newEvent.toString());
                System.out.println("Now you have " + counter + " tasks in the list.\n" + border);
            }
            else if(input.startsWith("todo")){
                String td = input.substring(5);
                Todo newToDo = new Todo(td);
                userList[counter] = newToDo;
                counter ++;
                System.out.println(border + "\nGot it. I've added this task:");
                System.out.println(newToDo.toString());
                System.out.println("Now you have " + counter + " tasks in the list.\n" + border);

            }else{
                System.out.println("Sorry wrong command! Please try again.");
            }

        }


    }
}
