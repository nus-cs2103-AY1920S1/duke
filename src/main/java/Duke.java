import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser();
        ArrayList<Tasks> listOfTasks = new ArrayList<Tasks>();

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");


        while(sc.hasNext()) {
            String nextCmd = sc.nextLine(); 
            ArrayList<String> descriptions = p.parse(nextCmd);
            ArrayList<String> taskDescriptions = new ArrayList<>();
            String keyword = descriptions.get(0);

            if(keyword.equals("bye")) {
                System.out.println("Bye! Hope to see you again!");
                break;
            }

            switch (keyword) {
                case "list":
                System.out.println(listOfTasks.size());
                for(int i = 1; i <= listOfTasks.size(); i++) {
                    System.out.println("in loop");
                    System.out.println(i + ". " + listOfTasks.get(i - 1));
                }
                //System.out.println("is listing");
                break;

                case "done":
                int indexOfTask = Integer.parseInt(descriptions.get(1));
                listOfTasks.get(indexOfTask - 1).finishTask();
                System.out.println("Task status updated.");
                sf.updateFile(listOfTasks);
                break;

                case "delete":
                int indexOfTask2 = Integer.parseInt(descriptions.get(1));
                listOfTasks.remove(indexOfTask2 - 1);
                System.out.println("List of tasks updated.");
                sf.updateFile(listOfTasks);
                break;

                case "todo":
                try {
                    taskDescriptions.add(descriptions.get(0));
                    taskDescriptions.add(descriptions.get(1));
                    Tasks todo = new Tasks(taskDescriptions);
                    listOfTasks.add(todo);
                    System.out.println("Got it, todo added to tasks");
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("OOPS!! The description for todo cannot be empty!");
                }  
                break;

                case "deadline":
                try {
                    taskDescriptions.add(descriptions.get(0));
                    taskDescriptions.add(descriptions.get(1));
                    taskDescriptions.add(descriptions.get(2));
                    Tasks deadline = new Tasks(taskDescriptions);
                    listOfTasks.add(deadline);
                    System.out.println("Got it, new deadline added to list");
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
                }  
                break;

                case "event":
                try {
                    taskDescriptions.add(descriptions.get(0));
                    taskDescriptions.add(descriptions.get(1));
                    taskDescriptions.add(descriptions.get(2));
                    Tasks event = new Tasks(taskDescriptions);
                    listOfTasks.add(event);
                    System.out.println("Got it, new event added to schedule");
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
                } 
                break;

                default:
                System.out.println("OOPS!! I'm sorry, but I don't know what that means :-("); 
                
            }
        }
           
    }
}
