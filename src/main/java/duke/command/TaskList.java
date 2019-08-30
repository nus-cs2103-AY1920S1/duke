package duke.command;

import duke.tasks.Task;

import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> li;
    
    public TaskList(LinkedList<Task> li){
        this.li = li;
    }

    public void printList(){
        String line =  "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\tHere are the Duke.tasks in your list:");
        for(int i = 0; i < li.size(); i++){
            int j = i+1;
            System.out.println("\t" + j + " " + li.get(i));
        }
        System.out.println(line);
    }

    public int size(){
        return li.size();
    }

    public Task getTask(int i){
        return li.get(i);
    }

    public void remove(int i){
        li.remove(i);
    }

    public void add(Task t){
        li.add(t);
    }

    public LinkedList<Task> getList(){
        return li;
    }


}