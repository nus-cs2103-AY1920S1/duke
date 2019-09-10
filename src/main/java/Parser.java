import java.text.ParseException;

public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui, Storage storage){
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    public void parse(String str) throws DukeException{
        try{
            String[] splited = str.split(" ");
            String check = splited[0].toLowerCase();
            
            //bye
            if (check.equals("bye")) {
                ui.bye();
                storage.saveFile(tasks);
                System.exit(0);
            //list
            }else if(check.equals("list")){
                ui.print(tasks.toString());
            //done
            }else if(check.equals("done")){
                String taskNum = splited[1];
                Task current = tasks.get(Integer.parseInt(taskNum) - 1);
                current.markAsDone();
                ui.print("Nice! I've marked this task as done:\n" + "    " + current.toString());
                storage.saveFile(tasks);

            }else if(check.equals("delete")){
                String taskNum = splited[1];
                Task current = tasks.get(Integer.parseInt(taskNum) - 1);
                tasks.remove(Integer.parseInt(taskNum) - 1);
                ui.print("Noted. I've removed this task: \n" + "    " + current.toString() + "\n     Now you have " + tasks.size() + " tasks in the list.");
                storage.saveFile(tasks);

            }else if(check.equals("todo")){
                String description = str.replace("todo", "").trim();
                //error handling
                if(description.equals("")){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                
                }else{ //successful addition 
                    tasks.add(new Todo(description));
                    Task current = tasks.get(tasks.size()-1);
                    ui.printMsg(current, tasks.size());
                    storage.saveFile(tasks);

                }
            //make an event task
            }else if(check.equals("event")){
                //removes the command and splits it into 2
                String [] splitDate = str.replace("event", "").split("/at");
                if(splitDate.length < 2){
                    throw new DukeException("    ☹ OOPS!!! Events require both a description and a date \n    (e.g. event go to concert /at 13 Feb)");
                }else{
                    //if it reaches here it is successful
                    tasks.add(new Event(splitDate[0].trim(), new DateTime(splitDate[1].trim())));
                    Task current = tasks.get(tasks.size()-1);
                    ui.printMsg(current, tasks.size());
                    storage.saveFile(tasks);
                }
            //make deadline task
            }else if(check.equals("deadline")){
                String [] splitDate = str.replace("deadline", "").split("/by");
                if(splitDate.length < 2){
                    throw new DukeException("☹ OOPS!!! Deadlines require both a description and a date by \n    (e.g. deadline homework3 /by tomorrow");
                }else{
                    tasks.add(new Deadline(splitDate[0].trim(), new DateTime(splitDate[1].trim())));
                    Task current = tasks.get(tasks.size()-1);
                    ui.printMsg(current, tasks.size());
                }
            //error handling
            }else{
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
    
        }catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Input cannot be empty!");
        }catch (ParseException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry,Please enter the date in the format dd-MM-yyyy HH:mm");
        }             
   
        
    }

}