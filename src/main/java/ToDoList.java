import java.util.Scanner;

public class ToDoList {
    public void run(){
        Scanner sc = new Scanner(System.in);
        String border = "    ____________________________________________________________";
        Task[] arr = new Task[100];
        int counter = 0;

        String input = sc.nextLine();

        while (!input.equals("bye")){ //first check for bye command
            if(input.equals("list")){ //check if list command
                System.out.println(border);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < counter; i++){
                    System.out.println("     " + (i + 1) + "." + arr[i]);
                }
                System.out.println(border);
            } else {
                String[] temp = input.split(" ");
                if (temp[0].equals("done")){ //command to add task as done
                    int done = Integer.parseInt(temp[1]) - 1;
                    arr[done].markAsDone();
                    System.out.println(border);
                    System.out.println("     Nice! I've marked this task as done: ");
                    System.out.println("       " + arr[done]);
                    System.out.println(border);

                } else { //command to add task to list

                    String date;
                    String message;
                    Boolean added = false;

                    try {
                        switch (temp[0]){
                            case "deadline":
                                date = input.substring(input.indexOf("/") + 4);
                                message = input.substring(input.indexOf(' ') + 1, input.indexOf("/") - 1);
                                arr[counter] = new Deadlines(message, date);
                                added = true;
                                break;
                            case "event":
                                date = input.substring(input.indexOf("/") + 4);
                                message = input.substring(input.indexOf(' ') + 1, input.indexOf("/") - 1);
                                arr[counter] = new Events(message, date);
                                added = true;
                                break;
                            case "todo":
                                if (temp.length < 2) throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
                                message = input.substring(input.indexOf(' ') + 1);
                                arr[counter] = new ToDos(message);
                                added = true;
                                break;
                        }

                        if (added) {
                            counter ++;
                            System.out.println(border);
                            System.out.println("     Got it. I've added this task: ");
                            System.out.println("       " + arr[counter - 1]);
                            System.out.printf("     Now you have %s tasks in the list.\n", counter);
                            System.out.println(border);
                        } else {
                            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }
                    catch (DukeException e){
                        System.out.println(e);
                    }
                    catch (StringIndexOutOfBoundsException e){
                        System.out.println(border);
                        System.out.println("     Please input a task and date");
                        System.out.println(border);
                    }

                }
            }

            input = sc.nextLine();
        }
        if (input.equals("bye")){
            System.out.println(border + "\n" + "     Bye. Hope to see you again soon!" + "\n" +border);
        }
    }
}
