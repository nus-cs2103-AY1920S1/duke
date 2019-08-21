import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static String message;
    public static List<Task> myList = new ArrayList<>();
    public static int idx = 0;
    public static String upperLine = "____________________________________________________________\n";
    public static String lowerLine = "____________________________________________________________\n";
    public static String[] oneLine;
    public static String[] timeDate;

    public static void childFeature() throws DukeException{
        System.out.print(upperLine);
        String description;
        String time_date;
        String firstWord = oneLine[0];

        if(!oneLine[1].isBlank() && oneLine.length == 2){
            System.out.println("Got it. I've added this task: ");
            //todo with description
            //deadline may not have /by
            //event may not have /at
            if(firstWord.equals("todo")){
                myList.add(new Todo(oneLine[1].trim()));
            }else if(firstWord.equals("deadline")){
                timeDate = oneLine[1].trim().split(" /by ");
                if(timeDate.length == 2){
                    description = timeDate[0].trim();
                    time_date = timeDate[1].trim();
                    myList.add(new Deadline(description, time_date));
                }else{
                    throw new DukeException("specific date/time for deadline is wrong");
                }
            }else{
                timeDate = oneLine[1].trim().split(" /at ");
                if(timeDate.length == 2){
                    description = timeDate[0].trim();
                    time_date = timeDate[1].trim();
                    myList.add(new Event(description, time_date));
                }else{
                    throw new DukeException("specific date/time for event is wrong");
                }
            }
        }else{
            throw new DukeException("The description of a " + firstWord + " cannot be empty");
        }
        System.out.println(myList.get(idx));
        idx++;
        System.out.println("Now you have "+ myList.size() + " tasks in the list.");
        System.out.println(lowerLine);
    }
    public static boolean isNumeric(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void doneFeature() throws DukeException{
        System.out.print(upperLine);
        //make sure it only have one number follow
//        System.out.println("oneLine.length: " + oneLine.length);
//        System.out.println("isNumeric(oneLine[1]) " + isNumeric(oneLine[1]));
//        System.out.println("!oneLine[1].isBlank() " + !oneLine[1].isBlank());

        if(oneLine.length != 1 && !oneLine[1].isBlank()
                && oneLine[1].trim().split(" ").length == 1 && isNumeric(oneLine[1].trim())){

            int i = Integer.parseInt(oneLine[1].trim());
            if(i <= myList.size() && i > 0){
                System.out.println("Nice! I've marked this task as done: ");
                Task current = myList.get(i - 1);
                current.markAsDone();
                System.out.println(current);
            }else{
                throw new DukeException("task number cannot markAsDone as it does not exist");
            }
        }
        else{
            throw new DukeException("check there is one and only one number follow by done");
        }
        System.out.println(lowerLine);
    }

    public static void deleteFeature() throws DukeException{
        String deleteMessage1 = "Noted. I've removed this task: ";
        String deleteMessage2 = "Now you have " + ( myList.size() - 1) + " tasks in the list.";
        System.out.print(upperLine);
        //make sure it only have one number follow
        if(oneLine.length != 1 && !oneLine[1].isBlank()
                && oneLine[1].trim().split(" ").length == 1 && isNumeric(oneLine[1])){
            int i = Integer.parseInt(oneLine[1]);
            if(i <= myList.size() && i > 0){
                System.out.println(deleteMessage1);
                Task delete_task = myList.get(i - 1);
                myList.remove(i - 1);
                System.out.println(delete_task);
                idx--;
            }else{
                throw new DukeException("task number cannot be deleted as it does not exist");
            }
        }
        else{
            throw new DukeException("check there is one and only one number follow by delete");
        }
        System.out.println(deleteMessage2);
        System.out.println(lowerLine);
    }

    public static void listFeature() throws DukeException{
        System.out.print(upperLine);
        if(oneLine.length == 1 || oneLine[1].isBlank()) {
            String title = "Here are the tasks in your list:\n";
            System.out.print(title);
            for(int i = 0; i < idx; i++){
                System.out.println((i+1) + ". " + myList.get(i));
            }
        }else{
            throw new DukeException("TThere is extra description for list");
        }
        System.out.println(lowerLine);
    }

    public static void byeFeature() throws DukeException{
        System.out.print(upperLine);
        if(oneLine.length == 1){
                message ="Bye. Hope to see you again soon!\n";
                System.out.print(message);
            }else{
                throw new DukeException("There is extra description for bye");
            }
        System.out.println(lowerLine);
    }

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\n"
        + "What can I do for you?\n";
        greet = upperLine + greet + lowerLine;
        System.out.println(greet);
            while(true){
                try{
                    String cmd = sc.nextLine();
                    oneLine = cmd.split(" ", 2);
                    String firstWord = oneLine[0];

                    if(firstWord.equals("bye")){
                        byeFeature();
                        break;
                    }else if(firstWord.equals("list")){
                        listFeature();
                    }else if(firstWord.equals("done")){
                        doneFeature();
                    }else if(firstWord.equals("delete")){
                        deleteFeature();
                    }else if(firstWord.equals("todo") || firstWord.equals("deadline")
                            ||firstWord.equals("event")){
                            childFeature();
                    }else{
                        //situation of firstWord is invalid
                        System.out.print(upperLine);
                        throw new DukeException("I'm sorry, but I don't know what that means :-");
                    }
            }catch(DukeException e){
                    System.out.println(e);
                    System.out.println(lowerLine);
            }
        }
    }
}