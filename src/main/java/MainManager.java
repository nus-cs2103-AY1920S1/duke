import java.util.Scanner;

public class MainManager {
    private Task[] list;
    private int counter;

    public MainManager() {
        this.list = new Task[100];
        counter = 0;
    }

    private String[] getEventDetails(String command) throws TaskException{
        if(command.length() <= 5) {
            throw new TaskException();
        }
        String woCommand = command.substring(6);
        return woCommand.split(" /at ");
    }

    private String[] getDeadlineDetails(String command) throws TaskException{
        if(command.length() <= 8) {
            throw new TaskException();
        }
        String woCommand = command.substring(9);
        return woCommand.split(" /by ");
    }


    public void run(){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String toPrint = sc.nextLine();
            String[] command = toPrint.split(" ");
            switch(command[0]) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":
                    for(int i = 0; i < list.length; i++) {
                        if(list[i] != null) {
                            System.out.println(i + 1 + "." + list[i]);
                        }
                    }
                    break;

                case "done" :
                    System.out.println("Nice! I've marked this task as done:");
                    Task currTask = list[Integer.parseInt(command[1]) - 1];
                    currTask.setDone();
                    System.out.println(" " + currTask);
                    break;

                case "todo":
                    try {
                        if(toPrint.length() <= 4) {
                            throw new TaskException();
                        }
                        String todoName = toPrint.substring(5);
                        Task todo = new Todo(todoName);
                        list[counter] = todo;
                        counter++;
                        System.out.println("Got it. I've added this task: ");
                        System.out.println("    " + todo);
                        System.out.println("Now you have " + counter + " tasks in the list.");

                    } catch(TaskException toEx)  {
                        System.out.println(toEx);
                    }
                    break;

                case "deadline":
                    try {
                        String[] deadlineDetails = getDeadlineDetails(toPrint);
                        Task deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        list[counter] = deadline;
                        counter++;
                        System.out.println("Got it. I've added this task: ");
                        System.out.println("    " + deadline);
                        System.out.println("Now you have " + counter + " tasks in the list.");
                    } catch(TaskException dlEx) {
                            System.out.println(dlEx);
                    }
                    break;

                case "event":
                    try {
                        String[] eventDetails = getEventDetails(toPrint);
                        Task event = new Event(eventDetails[0], eventDetails[1]);
                        list[counter] = event;
                        counter++;
                        System.out.println("Got it. I've added this task: ");
                        System.out.println("    " + event);
                        System.out.println("Now you have " + counter + " tasks in the list.");
                    } catch (TaskException evEx) {
                        System.out.println(evEx);
                    }
                    break;


                default:
                    /*
                    Task curr = new Task(toPrint);
                    list[counter] = curr;
                    counter++;
                    System.out.println("added: " + toPrint);
                    */
                    System.out.println("ERROR: Unsupported action");
                    break;


            }

            if(toPrint.equals("bye")) {
                break;
            }
        }
    }


}
