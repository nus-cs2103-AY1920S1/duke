package utils;

import java.text.ParseException;

import exceptions.DukeException;

public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui, Storage storage){
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }
    /**
     * 
     * @param str
     * @throws DukeException
     * takes user input and makes corresponding task object to be put inside tasklist 
     * error handling done through DukeException
     */
    public void parse(String str) throws DukeException{
        try{
            String[] splited = str.split(" ");
            String check = splited[0].toLowerCase();
            
            if (check.equals("bye")) {
                ui.bye();
                storage.saveFile(tasks);
                System.exit(0);

            }else if(check.equals("list")){
                ui.print(tasks.toString());
            
            }else if(check.equals("done")){
                int taskNum = Integer.parseInt(splited[1]) -1;
                tasks.markAsDone(taskNum);

            }else if(check.equals("delete")){
                int taskNum = Integer.parseInt(splited[1]) - 1;
                tasks.delete(taskNum);

            }else if(check.equals("todo")){
                String description = str.replaceFirst("todo", "").trim();
                
                if(description.equals("")){ //error handling
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                
                }else{ //successful addition 
                    tasks.addTodo(description);
                    Task current = tasks.get(tasks.size()-1);
                    ui.printMsg(current, tasks.size());

                }
            }else if(check.equals("event")){
                String [] splitDate = str.replaceFirst("event", "").split("/at");
                if(splitDate.length < 2){
                    throw new DukeException("☹ OOPS!!! Events require both a description and a date /at");
                }else{
                    //if it reaches here it is successful
                    tasks.addEvent(splitDate[0].trim(), new DateTime(splitDate[1].trim()));
                }
            }else if(check.equals("deadline")){
                String [] splitDate = str.replaceFirst("deadline", "").split("/by");
                if(splitDate.length < 2){
                    throw new DukeException("☹ OOPS!!! Deadlines require both a description and a date by");
                }else{
                    tasks.addDeadline(splitDate[0].trim(), new DateTime(splitDate[1].trim()));
                }
            }else if(check.equals("find")){
                String keyword = str.replaceFirst("find", "").trim();
                tasks.find(keyword);
            
            }else{ //error handling
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
    
        }catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Input cannot be empty!");
        }catch (ParseException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry,Please enter the date in the format dd-MM-yyyy HH:mm");
        }             
        
        storage.saveFile(tasks);
        
    }


}