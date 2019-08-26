package duke;

import duke.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public ArrayList<Task> getTaskList(){
        return this.tasks;
    }

    public Task add(String description, TaskEnum type){
        // duke.task.Event and duke.task.Deadline default date is based on the system clock
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.US));
        switch(type){
        case TODO:
            Todo todo = new Todo(description);
            tasks.add(todo);
            return todo;
        case DEADLINE:
            Deadline deadline = new Deadline(description, date);
            tasks.add(deadline);
            return deadline;
        case EVENT:
            Event event = new Event(description, date);
            tasks.add(event);
            return event;
        default:
            break;
        }
        return null;
    }

    public Task add(String description, String date, TaskEnum type){
        switch(type){
        case TODO:
            Todo todo = new Todo(description);
            tasks.add(todo);
            return todo;
        case DEADLINE:
            Deadline deadline = new Deadline(description, date);
            tasks.add(deadline);
            return deadline;
        case EVENT:
            Event event = new Event(description, date);
            tasks.add(event);
            return event;
        default:
            break;
        }
        return null;
    }

    public Task delete(int taskNo){
        try {
            if(tasks.size() > 0){
                Task task = tasks.remove(taskNo - 1);  //Minus 1 because the displayed list starts at 1
                return task;
            }
            else{
                throw new DukeException("There are no items in the list.");
            }
        } catch(DukeException de){
        } catch(NumberFormatException nfe) {
            new DukeException("Only numbers are allowed.");
        } catch(IndexOutOfBoundsException ioobe){
            new DukeException("There is no such item in the list.");
        }
        return null;
    }

    public Task done(int taskNo){
        try {
            Task task = tasks.get(taskNo - 1); //Minus 1 because the displayed list starts at 1
            if(task.getIsDone()){
                throw new DukeException("This item has already been checked.");
            }
            else {
                task.markAsDone();
                return task;
            }
        } catch(DukeException de){
        } catch(NumberFormatException nfe) {
            new DukeException("Only numbers are allowed.");
        } catch(IndexOutOfBoundsException ioobe) {
            new DukeException("There is no such item in the list.");
        }
        return null;
    }

    public String list(){
        String listOutput = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            //Get tasks
            Task task = tasks.get(i);

            listOutput += (i + 1) + "." + task;

            if (i + 1 != tasks.size()) {
                listOutput += "\n";
            }
        }

        return listOutput;
    }

}
