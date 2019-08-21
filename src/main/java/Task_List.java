import java.util.ArrayList;

public class Task_List {
    ArrayList <Task> schedule = new ArrayList<> ();
    int task_Num = 0;

    public void add(String task){
        String[] word_Arr = task.split(" ", 2);
        switch (word_Arr[0]){
            case "list":
                System.out.println(this);
                break;

            case "done":
                try{
                    try {
                        if (word_Arr.length < 2)
                            throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Which task did you complete again?\n" + (new Border()));
                        schedule.get(Integer.parseInt(word_Arr[1]) - 1).markAsDone();
                        System.out.println(new Border());
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       " + schedule.get(Integer.parseInt(word_Arr[1]) - 1).toString());
                        System.out.println(new Border());
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                        throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Index out of bounds.\n" + (new Border()));
                    } catch (NumberFormatException e) {
                        throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Please enter a single integer index of task to delete.\n" + (new Border()));
                    }
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                } finally{
                    break;
                }

            case "delete":
                try{
                    try {
                        if (word_Arr.length < 2)
                            throw new DukeException((new Border()) + "\n     ☹ OOPS!!! What do you want to delete again?\n" + (new Border()));
                        Task removed_Task = schedule.get(Integer.parseInt(word_Arr[1]) - 1);
                        schedule.remove(removed_Task);
                        task_Num --;
                        System.out.println(new Border());
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + removed_Task.toString());
                        System.out.println("      Now you have " + task_Num + " tasks in the list.");
                        System.out.println(new Border());
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                        throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Index out of bounds.\n" + (new Border()));
                    } catch (NumberFormatException e) {
                        throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Please enter a single integer index of task to delete.\n" + (new Border()));
                    }
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
            default:
                try {
                    Task new_task = track(word_Arr);
                    schedule.add(new_task);
                    task_Num ++;
                    System.out.println(new Border());
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + new_task.toString());
                    System.out.println("     Now you have " + task_Num + " tasks in the list.");
                    System.out.println(new Border() + "\n");
                    break;
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                }
            }
        }

    private Task track(String[] word_Arr) throws DukeException{
        try {
            if (word_Arr[0].equals("todo")) {
                check_Decription(word_Arr, "todo");
                return new Todo(word_Arr[1]);
            } else if (word_Arr[0].equals("deadline")) {
                check_Decription(word_Arr, "deadline");
                return new Deadline(word_Arr[1]);
            } else if (word_Arr[0].equals("event")){
                check_Decription(word_Arr, "event");
                return new Event(word_Arr[1]);
            } else {
                throw new DukeException((new Border()) + "\n     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + (new Border()), new IllegalArgumentException());
            }
        } catch(DukeException e){
            throw e;
        }
    }

    private void check_Decription(String[] word_Arr, String task_Type) throws DukeException{
        if (word_Arr.length < 2) {
            throw new DukeException("     ☹ OOPS!!! The description of a " + task_Type + " cannot be empty.\\n\" + (new Border())");
        }
    }


    public String toString(){
        String output = (new Border()) + "\n" + "     Here are the tasks in your list: \n";
        for (int index = 0; index < task_Num; index ++){
            Task task = schedule.get(index);
            output += ("     " + (index + 1) + "." + task.toString() + "\n");
        }
        return output + (new Border()) + "\n";
    }
}
