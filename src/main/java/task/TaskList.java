package task;

import exception.DukeException;
import filewriter.Storage;


import java.util.ArrayList;


public class TaskList {
    ArrayList <Task> schedule = new ArrayList<> ();
    public int task_Num;
    public boolean isFirst ;

    public TaskList(Storage storage){
        try {
            this.schedule = storage.getSchedule();
            task_Num = schedule.size();
            if (task_Num == 0){
                isFirst = true;
            } else {
                isFirst = false;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public TaskList(){
        this.schedule = schedule;
        task_Num = schedule.size();
        if (task_Num == 0){
            isFirst = true;
        } else {
            isFirst = false;
        }
        System.out.println(task_Num);
    }

    public Task getTask(int index){
        return schedule.get(index);
    }

    public Task complete(int index)
            throws NullPointerException, IndexOutOfBoundsException, NumberFormatException, DukeException{
        Task completedTask = schedule.get(index);
        completedTask.markAsDone();
        return completedTask;
    }

    public void addTask(Task task) throws DukeException {
        schedule.add(task);
        task_Num++;
    }

    public Task remove(int index)
            throws NullPointerException, IndexOutOfBoundsException, NumberFormatException, DukeException{
        Task removed_Task = schedule.get(index);
        schedule.remove(index);
        task_Num --;
        return removed_Task;
    }


    public ArrayList<Task> getList(){
        return schedule;
    }

    public String toString(){
        String output = "";
        if (task_Num != 0){
            for (int index = 0; index < task_Num; index ++){
                Task task = schedule.get(index);
                output += ("     " + (index + 1) + "." + task.toString() + "\n");
            }
            return output.substring(0, output.length() - 1);
        } else {
            return output;
        }
    }
}
