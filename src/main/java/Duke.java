import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args){
        String startingMessage = "Hello! I'm Duke\nWhat can I do for you?" ;
        System.out.println(startingMessage);
        List <Task> listOfStuff = new ArrayList <Task>();
        
        boolean flag = true;
        while(flag){
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if(line.equals("bye")){
                flag = false;
                System.out.println("Bye. Hope to see you again soon!");
            }else if(line.equals("list")){
                for (int i = 0; i < listOfStuff.size(); i++) {
                    Integer number = i + 1;
                    String message = number + ". " + listOfStuff.get(i);
                    System.out.println(message);
                  }
            }else{ 
                String inputnoun = line.split(" ")[0];

                try{
                    if((!inputnoun.equals("done")) &&
                    (!inputnoun.equals("todo")) &&
                    (!inputnoun.equals("deadline")) &&
                    (!inputnoun.equals("event")) ){
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("); 

                    }else if((line.split(" ", 2)).length < 2){
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }else{
        
                        String taskName = line.split(" ", 2) [1];
                        
                        taskName = line.split(" ", 2)[1];
                        if(inputnoun.equals("done")){
                            for (int i = 0; i < listOfStuff.size(); i++) {
                                Integer indexOfTask = i + 1;
                                String stringFromUser = line.replaceAll("\\D+","");
                                Integer indexFromUser = Integer.parseInt(stringFromUser);
                                if(indexOfTask == indexFromUser ){
                                    Task currentTask = listOfStuff.get(i);
                                    currentTask.setStatus(true);
                                    String message = "Nice! I've marked this task as done:\n" + currentTask;
                                    System.out.println(message);
                                    }
                                }
                        }else if (inputnoun.equals("todo")){
                            ToDoTask newTask = new ToDoTask (false, taskName);
                            listOfStuff.add(newTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(newTask);
                            System.out.println(String.format("Now you have %d tasks in the list.", listOfStuff.size() ));
                        }else if (inputnoun.equals("deadline")){
                            String [] deadlineArray = line.split("/");
                            if(deadlineArray.length < 2){
                                throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty."); 
                            }
                            String deadline = deadlineArray[1];
                            String newDeadLine = "(by:" +deadline.replace("by", "")  + ")";
                            String newTaskName = taskName.split("/")[0];
                            DeadlinesTask newTask = new DeadlinesTask (false, newTaskName, newDeadLine);
                            listOfStuff.add(newTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(newTask);
                            System.out.println(String.format("Now you have %d tasks in the list.", listOfStuff.size() ));
                        }else if (inputnoun.equals("event")){
                            String [] deadlineArray = line.split("/");
                            if(deadlineArray.length < 2){
                                throw new DukeException("☹ OOPS!!! The date of an event cannot be empty."); 
                            }
                            String deadline = deadlineArray[1];
                            String newDeadLine = "(at:" +deadline.replace("at", "")  + ")";                    
                            String newTaskName = taskName.split("/")[0];
                            EventsTask newTask = new EventsTask (false, newTaskName, newDeadLine);
                            listOfStuff.add(newTask);
                            System.out.println(newTask);
                            System.out.println(String.format("Now you have %d tasks in the list.", listOfStuff.size() ));
                        }
                    }
                }catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
