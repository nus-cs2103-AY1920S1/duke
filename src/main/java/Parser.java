public class Parser{
    
    private Storage storage;
    private TaskList taskList;

    public Parser(Storage storage, TaskList taskList){
        this.storage = storage;
        this.taskList = taskList;

    }

    public boolean parse (String line){

        if(line.equals("bye")){
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        }else if(line.equals("list")){
            taskList.printTaskList();
            return true;
        }else{ 
            String inputnoun = line.split(" ")[0];

            try{
                if((!inputnoun.equals("done")) &&
                (!inputnoun.equals("todo")) &&
                (!inputnoun.equals("deadline")) &&
                (!inputnoun.equals("event")) &&
                (!inputnoun.equals("delete"))&&
                (!inputnoun.equals("find")) ){
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("); 

                }else if((line.split(" ", 2)).length < 2){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }else{
    
                    String taskName = line.split(" ", 2) [1];
                    
                    if(inputnoun.equals("done")){
                        String stringFromUser = line.replaceAll("\\D+","");
                        Integer indexFromUser = Integer.parseInt(stringFromUser);
                        taskList.doneTask(indexFromUser - 1);
                        storage.save();
                            
                    }else if (inputnoun.equals("todo")){
                        ToDoTask newTask = new ToDoTask (false, taskName);
                        taskList.add(newTask);
                        storage.save();

                    }else if (inputnoun.equals("deadline")){
                        String [] deadlineArray = line.split("/");
                        if(deadlineArray.length < 2){
                            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty."); 
                        }
                        
                        int date =  Integer.parseInt(deadlineArray[1].replace("by ", ""));
                        String month = deadlineArray[2];
                        int year = Integer.parseInt(deadlineArray[3]);
                        int hour = Integer.parseInt(deadlineArray[4]);
                        int min = Integer.parseInt(deadlineArray[5]);

                        DateTime deadlineDateTime = new DateTime(hour, min, date, month, year);
                        String newTaskName = taskName.split("/")[0];
                        
                        DeadlinesTask newTask = new DeadlinesTask (false, newTaskName, deadlineDateTime);
                        taskList.add(newTask);
                        storage.save();
                    
                    }else if (inputnoun.equals("event")){
                        String [] deadlineArray = line.split("/");
                        if(deadlineArray.length < 2){
                            throw new DukeException("☹ OOPS!!! The date of an event cannot be empty."); 
                        }
                        String newTaskName = taskName.split("/")[0];

                        int startingDate =  Integer.parseInt(deadlineArray[1].replace("at ", ""));
                        String startingMonth = deadlineArray[2];
                        int startingYear = Integer.parseInt(deadlineArray[3]);
                        int startingHour = Integer.parseInt(deadlineArray[4]);
                        int startingMin = Integer.parseInt(deadlineArray[5]);

                        DateTime startingDateTime = new DateTime(startingHour, startingMin, startingDate, startingMonth, startingYear);

                        int endingDate =  Integer.parseInt(deadlineArray[6]);
                        String endingMonth = deadlineArray[7];
                        int endingYear = Integer.parseInt(deadlineArray[8]);
                        int endingHour = Integer.parseInt(deadlineArray[9]);
                        int endingMin = Integer.parseInt(deadlineArray[10]);

                        DateTime endingDateTime = new DateTime(endingHour, endingMin, endingDate, endingMonth, endingYear);

                        EventsTask newTask = new EventsTask (false, newTaskName, startingDateTime, endingDateTime);
                        taskList.add(newTask);
                        storage.save();

                    }else if(inputnoun.equals("find")){
                        Command findCommand = new FindCommand(taskName);
                        findCommand.execute(taskList);



                    }else{
                        int offset = Integer.parseInt(taskName) - 1;
                        if(offset > taskList.size() - 1){
                            throw new DukeException("☹ OOPS!!! There aren't so many tasks!"); 
                        }else{
                            taskList.deleteTask(offset);
                        }
                        storage.save();
                    }
                    return true;
                }
            }catch(DukeException e){
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
}