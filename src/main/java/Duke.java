import duke.command.AException;
import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Duke {
    
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.printFileContents());
        } catch (FileNotFoundException e){

        }

    }

    //write to file when there is a change in state

    public void run(){
        Scanner scan = new Scanner(System.in);
        
        ui.showWelcome();

        //LinkedList used to store all the String given by the user;
        // LinkedList<Task> li = new LinkedList<Task>();

        //read the existing task from the file and create the Duke.tasks to put
        //into the list. 

        // try{
        //     storage.printFileContents(li);
        // } catch(FileNotFoundException e){
        //     //System.out.println("File not found");
        // }

        while(true){
            ui.printnewline();
            String echo = scan.nextLine();
            Parser split = new Parser(echo);
            String error = "";

            String firstWord = split.getType();

            if(firstWord.equals("bye")){
                // if bye, then end the program
                ui.printline();
                System.out.println("\tBye. Hope to see you again soon!");
                ui.printline();
                
                
                break;

            } else if(firstWord.equals("list")){
                //if list, print the list of Duke.tasks

                tasks.printList();

            } else if(firstWord.equals("done")){
                //if done, change the task status and tell them what they have completed
                int taskNum = Integer.parseInt(split.getDesc().get(0));
                //scan.nextLine();
                //System.out.println(taskNum);
                
                int taskNumb = taskNum - 1;
                
                if (taskNumb >= tasks.size()){
                    error = "taskDoNotExist";
                } else if (tasks.getTask(taskNumb).getIsDone()) {
                    //System.out.println("im here");
                    error = "taskAlreadyCompleted";
                } else {
                    Task change = tasks.getTask(taskNumb);
                    change.completed();
                    ui.printDone(change);
                }

            } else if (firstWord.equals("delete")){
                int taskNum = Integer.parseInt(split.getDesc().get(0));
                int taskNumb = taskNum - 1;

                if(taskNumb >= tasks.size()){
                    error = "taskDoNotExist";
                } else {
                    ui.printDelete(tasks.getTask(taskNumb), tasks.size() - 1);
                    tasks.remove(taskNumb);
                }

            } else {
                String actual =  "";
                Task newTask = null;
                LinkedList<String> copy = split.getDesc();

                if (firstWord.equals("todo")){
                    actual =  String.join(" ", copy);
                    if(actual.isEmpty()){
                        error = "emptyToDo";
                    }
                    newTask =  new ToDo(actual);
                } else if (firstWord.equals("deadline")){
                    String help = String.join(" ", copy);

                    String task = "";
                    String time = "";

                    if(help.isEmpty()){
                        error = "emptyDeadline";
                    } else {
                        int slashInt = help.indexOf("/by");
                        //System.out.println(slashInt);
                        //time = help.substring();
                        if(slashInt == -1){
                            error = "emptyBy";
                        } else {
                            time = help.substring(slashInt + 4);
                            task = help.substring(0, slashInt - 1);

                            if (task.equals(" ")){
                                error = "emptyDeadline";
                            } else {
                                //System.out.println(task);
                                newTask = new Deadline(task, storage.dateTimeConversion(time));
                                System.out.println(time);
                            }
                        }

                    }
 
                } else if(firstWord.equals("event")){
                    String help = String.join(" ", copy);

                    String task = "";
                    String time = "";

                    if(help.isEmpty()){
                        error = "emptyEvent";
                    } else {
                        int slashInt = help.indexOf("/at");
                        //System.out.println(slashInt);
                        //time = help.substring();
                        if(slashInt == -1){
                            error = "emptyAt";
                        } else {
                            time = help.substring(slashInt + 4);
                            task = help.substring(0, slashInt - 1);
                            if (task.equals(" ")){
                                error = "emptyEvent";
                            } else {
                                //System.out.println(task);
                                newTask = new Event(task, storage.dateTimeConversion(time));
                            }
                        }

                    }
                }

                //handle all errors
                if (!error.isEmpty() || newTask == null){
                    
                    AException ee =  new AException();
                    ui.printline();
                    if (error.equals("emptyToDo")){
                        ee.emptyToDoException();
                    } else if (error.equals("emptyDeadline")){
                        ee.emptyDeadlineException();
                    } else if (error.equals("emptyBy")){
                        ee.emptyByException();
                    } else if (error.equals("emptyEvent")){
                        ee.emptyEventException();
                    } else if (error.equals("emptyAt")){
                        ee.emptyAtException();
                    } else {
                        ee.dontUnderstand();
                    }

                    ui.printline();
                    error = "";
                } else {
                    tasks.add(newTask);
                    ui.takeInput(newTask, tasks.size());
                    
                }

            }

            if(!error.isEmpty()){
                AException ee2 =  new AException();
                ui.printline();
                if(error.equals("taskDoNotExist")){
                    ee2.exceedListSize();
                } else if(error.equals("taskAlreadyCompleted")){
                    ee2.taskAlreadyCompleted();
                }
                ui.printline();
            }

            try{
                storage.writeFile(tasks.getList());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
