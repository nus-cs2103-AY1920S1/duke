package duke;

import duke.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Creates a Tasklist object.
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Returns the task list as an ArrayList.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList(){
        return this.tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param description Description of task.
     * @param type Type of task.
     * @return Task that was added to the list.
     */
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

    /**
     * Adds a new task to the list.
     *
     * @param description Description of task.
     * @param date Date of task.
     * @param type Type of task.
     * @return Task that was added to the list.
     */
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

    /**
     * Delete a task from the list.
     *
     * @param taskNo Task number as specified on the list.
     * @return Task that was deleted.
     */
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

    /**
     * Mark a task as completed.
     *
     * @param taskNo Task number as specified on the list.
     * @return Task that was marked completed.
     */
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

    /**
     * Returns list of tasks.
     *
     * @return List of tasks
     */
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
