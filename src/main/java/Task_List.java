public class Task_List {
    String [] schedule = new String [100];
    int task_Num = 0;
    public void add(String task){
        if (!task.equals("list")){
            schedule[task_Num++] = task;
            System.out.println("\n" + new Border());
            System.out.println("     added: " + task);
            System.out.println("\n" + new Border() + "\n");
        }
    }

    public String toString(){
        String output = "\n" + (new Border()) + "\n";
        for (int index = 0; index <= task_Num; index ++){
            String task = schedule[index];
            if (task == null){
                break;
            } else {
                output += ("     " + (index + 1) + ". " + schedule[index] + "\n");
            }
        }
        return output + "\n" + (new Border());
    }
}
