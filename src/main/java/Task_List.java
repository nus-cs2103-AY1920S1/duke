import java.util.ArrayList;


public class Task_List {
    ArrayList <Task> schedule = new ArrayList<> ();
    int task_Num = 0;

    public void add(String task){
        String[] word_Arr = task.split(" ", 2);
        try {
            Commands command = Commands.getByName(word_Arr[0]);
            switch (command) {
                case LIST:
                    System.out.println(this);
                    break;

                case DONE:
                    try {
                        try {
                            if (word_Arr.length < 2)
                                throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Which task did you complete again?\n" + (new Border()) + "\n");
                            schedule.get(Integer.parseInt(word_Arr[1]) - 1).markAsDone();
                            System.out.println(new Border());
                            System.out.println("     Nice! I've marked this task as done:");
                            System.out.println("       " + schedule.get(Integer.parseInt(word_Arr[1]) - 1).toString());
                            System.out.println(new Border());
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Index out of bounds.\n" + (new Border()) + "\n");
                        } catch (NumberFormatException e) {
                            throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Please enter a single integer index of task to delete.\n" + (new Border()) + "\n");
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                case DELETE:
                    try {
                        try {
                            if (word_Arr.length < 2)
                                throw new DukeException((new Border()) + "\n     ☹ OOPS!!! What do you want to delete again?\n" + (new Border()) + "\n");
                            Task removed_Task = schedule.get(Integer.parseInt(word_Arr[1]) - 1);
                            schedule.remove(removed_Task);
                            task_Num--;
                            System.out.println(new Border());
                            System.out.println("     Noted. I've removed this task:");
                            System.out.println("       " + removed_Task.toString());
                            System.out.println("      Now you have " + task_Num + " tasks in the list.");
                            System.out.println(new Border());
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Index out of bounds.\n" + (new Border()) + "\n");
                        } catch (NumberFormatException e) {
                            throw new DukeException((new Border()) + "\n     ☹ OOPS!!! Please enter a single integer index of task to delete.\n" + (new Border()) + "\n");
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }
                default:
                    try {
                        Task new_task = track(command, word_Arr);
                        schedule.add(new_task);
                        task_Num++;
                        System.out.println(new Border());
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + new_task.toString());
                        System.out.println("     Now you have " + task_Num + " tasks in the list.");
                        System.out.println(new Border() + "\n");
                        break;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }

    private Task track(Commands command, String[] word_Arr) throws DukeException{
        try {
            switch (command) {
                case TODO:
                    check_Decription(word_Arr, "todo");
                    return new Todo(word_Arr[1]);
                case DEADLINE:
                    check_Decription(word_Arr, "deadline");
                    return new Deadline(word_Arr[1]);
                default:
                    check_Decription(word_Arr, "event");
                    return new Event(word_Arr[1]);
                }
        } catch(DukeException e){
            throw e;
        }
    }

    private void check_Decription(String[] word_Arr, String task_Type) throws DukeException{
        if (word_Arr.length < 2) {
            throw new DukeException((new Border()) + "\n     ☹ OOPS!!! The description of a " + task_Type + " cannot be empty.\n" + (new Border()));
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
