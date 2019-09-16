package utils;

import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui){
        this.tasks = tasks;
        this.ui = ui;
    }

    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task){
        tasks.add(task);
    }

    public int size(){
        return tasks.size();
    }

    public Task get(int i){
        return tasks.get(i);
    }

    public void addTodo(String description){
        tasks.add(new Todo(description));
    }

    public void addEvent(String description, DateTime datetime){
        Task current = new Event(description, datetime);
        tasks.add(current);
        ui.printMsg(current, tasks.size());
    }

    public void addDeadline(String description, DateTime datetime){
        Task current = new Deadline(description, datetime);
        tasks.add(current);
        ui.printMsg(current, tasks.size());
    }  

    public void delete(int i){
        Task current = tasks.get(i);
        tasks.remove(i);
        ui.printTab("Noted. I've removed this task: \n" + "    " + current.toString() + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    public void markAsDone(int i){
        Task current = tasks.get(i);
        current.markAsDone();
        ui.printTab("Nice! I've marked this task as done:\n" + "    " + current.toString());
    }

    public void find(String keyword){
        TaskList matches = new TaskList();

        for(int i = 0; i < tasks.size(); i++){
            String taskName = tasks.get(i).getTaskName();

            if(taskName.contains(keyword)){
                matches.add(tasks.get(i));
            }
            
        }

        ui.print("    Here are the matching tasks in your list: \n" + 
                matches.toString());
    }

    @Override
    public String toString(){
        String output = ""; 
        for(int i = 0; i < tasks.size(); i++){
            output = output + "     " +  (i+1) + ": " + tasks.get(i).toString();
            if(i < tasks.size() - 1){
                output = output + "\n";
            }
        }
        return output;
    }
}