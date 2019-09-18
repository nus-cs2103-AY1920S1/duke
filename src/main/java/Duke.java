import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser();
        TaskList listOfTasks = new TaskList();
        SaveToFile sf = new SaveToFile();
        
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
                for(int i = 1; i <= listOfTasks.size(); i++) {
                    System.out.println(i + ". " + listOfTasks.getTask(i - 1));
                }
                break;

                case "done":
                int indexOfTask = Integer.parseInt(descriptions.get(1));
                listOfTasks.getTask(indexOfTask - 1).finishTask();
                System.out.println("Task status updated.");
                sf.updateFile(listOfTasks.getTaskList());
                break;

<<<<<<< Updated upstream
                case "delete":
                int indexOfTask2 = Integer.parseInt(descriptions.get(1));
                listOfTasks.deleteTask(indexOfTask2 - 1);
                System.out.println("List of tasks updated.");
                sf.updateFile(listOfTasks.getTaskList());
                break;
=======
    private String dukeRun(String input) {
        String output;
        try {
            String fullCommand = input;
            Command c = Parser.parse(fullCommand);
            output = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            output = ui.showError(e.getMessage());
        }
        return output;
    }
>>>>>>> Stashed changes

                case "todo":
                try {
                    taskDescriptions.add(descriptions.get(0));
                    taskDescriptions.add(descriptions.get(1));
                    Tasks todo = new Tasks(taskDescriptions);
                    listOfTasks.addTask(todo);
                    sf.updateFile(listOfTasks.getTaskList());
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
                    listOfTasks.addTask(deadline);
                    sf.updateFile(listOfTasks.getTaskList());
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
                    listOfTasks.addTask(event);
                    sf.updateFile(listOfTasks.getTaskList());
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
