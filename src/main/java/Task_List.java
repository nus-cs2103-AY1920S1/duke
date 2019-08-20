public class Task_List {
    Task [] schedule = new Task [100];
    int task_Num = 0;

    public void add(String task){
        String[] word_Arr = task.split(" ");
        switch (word_Arr[0]){
            case "list":
                System.out.println(this);
                break;

            case "done":
                schedule[Integer.parseInt(word_Arr[1]) - 1].markAsDone();
                System.out.println(new Border());
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println("       " + schedule[Integer.parseInt(word_Arr[1]) - 1].toString());
                System.out.println(new Border());
                break;

            default:
                Task new_task = track(task);
                schedule[task_Num++] = new_task;
                System.out.println(new Border());
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + new_task.toString());
                System.out.println("     Now you have " + task_Num + " tasks in the list.");
                System.out.println(new Border() + "\n");
                break;
        }
    }

    private Task track(String task){
        String[] word_Arr = task.split(" ");
        if (word_Arr[0].equals("todo")){
            return new Todo(task.split(" ", 2)[1]);
        } else if (word_Arr[0].equals("deadline")){
            return new Deadline(task.split(" ", 2)[1]);
        } else {
            return new Event(task.split(" ", 2)[1]);
        }
    }

    public String toString(){
        String output = (new Border()) + "\n" + "     Here are the tasks in your list: \n";
        for (int index = 0; index <= task_Num; index ++){
            Task task = schedule[index];
            if (task == null){
                break;
            } else {
                /*System.out.println(index + 1);
                System.out.println(schedule[index]);
                String task = schedule[index].toString();*/
                output += ("     " + (index + 1) + "." + task.toString() + "\n");
            }
        }
        return output + (new Border()) + "\n";
    }
}
