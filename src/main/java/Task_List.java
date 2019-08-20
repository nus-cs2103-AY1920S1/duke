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
                schedule[task_Num++] = new Task(task);
                System.out.println(new Border());
                System.out.println("     added: " + task);
                System.out.println(new Border() + "\n");
                break;
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
