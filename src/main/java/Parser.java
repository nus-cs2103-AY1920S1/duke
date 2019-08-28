public class Parser{

       ////////////////// Handle method ///////////////////////////////////
        /*
       if (userInput.equals("bye")) {
        System.out.println("Bye. Hope to see you again.");
        break;
       }


        if (userInput.contains("todo")) {
        no_of_task++;
        String sub = userInput.substring(5);
        if (sub.isEmpty()) {
            System.out.println("OOPS!! The description of a todo cannot be empty.");
        } else {
            System.out.println("Got it. I've added this task:");
            System.out.println("  [ ][ ]" + sub);
            System.out.println("Now you have " + no_of_task + " tasks in the list.");
            Task t = new Task(sub, 'T', 0, "");
            taskList.add(t);
            AutoSave(taskList, no_of_task);
        }
    } else {

        if (userInput.equals("list")) {
            System.out.println("Here are the tasks in your list:");

            for (int i = 0; i < no_of_task; i++) {
                if((taskList.get(i).timeframe).equals("")){
                    System.out.println((i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description);
                }
                else
                    System.out.println((i + 1) + "." + "[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " (" + taskList.get(i).timeframe + ")");
            }
        } else {
            if (userInput.contains("done")) {
                String taskNumber = userInput.substring(5);

                //iterate through the tasks Arraylist until task is found
                for (int i = 1; i <= taskList.size(); i++) {
                    if (i == (Integer.parseInt(taskNumber))) {
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("  [" + "\u2713" + "] " + taskList.get(i - 1).description);
                        taskList.get(i - 1).changeStatus(1);
                        System.out.println("New status: " + taskList.get(i - 1).status);
                        AutoSave(taskList, no_of_task);
                    }
                }
            } else {
                if (userInput.contains("deadline")) {
                    int index = 0;
                    index = userInput.indexOf('/'); //iterate through the input to find the '/' char

                    String timeFrame = userInput.substring(index + 1);
                    String temp = timeFrame.substring(3);
                    String sub = userInput.substring(9, index - 1);

                    String new_timeFrame = Convert(temp);
                    Task t = new Task(sub, 'D', 0, new_timeFrame);
                    taskList.add(t);
                    no_of_task++;
                    AutoSave(taskList, no_of_task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
                    System.out.println("Now you have " + no_of_task + " tasks in the list.");
                } else {
                    if (userInput.contains("event")) {
                        int index = 0;
                        index = userInput.indexOf('/');   //iterate through the input to find the '/' char

                        String timeFrame = userInput.substring(index + 1);
                        String temp = timeFrame.substring(3);
                        String sub = userInput.substring(6, index - 1);

                        String new_timeFrame = Convert(temp);
                        Task t = new Task(sub, 'E', 0, new_timeFrame);
                        taskList.add(t);
                        no_of_task++;
                        AutoSave(taskList, no_of_task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  [ ][ ] " + sub + " (" + timeFrame + ")");
                        System.out.println("Now you have " + no_of_task + " tasks in the list.");
                    } else {
                        if (userInput.contains("delete")) {
                            int index = Integer.parseInt(userInput.substring(7));  //task to be deleted
                            Task t = taskList.get(index - 1);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  [" + t.type + "][" + t.status + "] " + t.description + " (" + t.timeframe + ")");
                            taskList.remove(index - 1);
                            no_of_task--;
                            AutoSave(taskList, no_of_task);
                            System.out.println("Now you have " + no_of_task + " tasks in the list.");
                        } else {
                            if (userInput.contains("find")){
                                int num=0;                                       //position of tasks, to be printed
                                String keyword = userInput.substring(5);       //keyword to be found
                                System.out.println("Here are the matching tasks in your list: ");

                                //Iterate the Task ArrayList to get the tasks
                                for (int i = 0; i < taskList.size(); i++){
                                    if((taskList.get(i).description).contains(keyword)) {    //if task description contains the keyword
                                        num++ ;
                                        System.out.println(num + ".[" + taskList.get(i).type + "][" + taskList.get(i).status + "] " + taskList.get(i).description + " " + taskList.get(i).timeframe);
                                    }
                                }

                            }
                            else
                                System.out.println("OOPS!! I'm sorry, but I don't know what that means.");
                        }
                    }
                }
            }
        }
    }
      */ ///////////////   End of handle method //////////////////////////////////
}
