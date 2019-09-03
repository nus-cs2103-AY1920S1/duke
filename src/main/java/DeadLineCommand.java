public class DeadLineCommand extends Command {

    public DeadLineCommand(String command, TaskList taskList ){
        super(command, taskList);
    }

    @Override
    public String processCommand(){
        String line = super.command;
        String taskName = line.split(" ", 2) [1];
        String [] deadlineArray = line.split("/");

        int date =  Integer.parseInt(deadlineArray[1].replace("by ", ""));
        String month = deadlineArray[2];
        int year = Integer.parseInt(deadlineArray[3]);
        int hour = Integer.parseInt(deadlineArray[4]);
        int min = Integer.parseInt(deadlineArray[5]);

        DateTime deadlineDateTime = new DateTime(hour, min, date, month, year);
        String newTaskName = taskName.split("/")[0];        
        DeadlinesTask newTask1 = new DeadlinesTask (false, newTaskName, deadlineDateTime);
        String toPrint1 = taskList.add(newTask1);
        return toPrint1;
    }

    @Override
    public String execute(String processedCommand){
        return processedCommand;
    }
    
}