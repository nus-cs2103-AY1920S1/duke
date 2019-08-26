import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    /*
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
     */
    private static ArrayList<Task> arrlist = new ArrayList<>();
    private static void loadtxt(String filePath) throws FileNotFoundException {
        File txtfile = new File(filePath);
        Scanner s = new Scanner(txtfile);
        while (s.hasNext()) {
            String txttasks = s.nextLine();
            String[] txtsplit = txttasks.split(",");
            if (txtsplit[0].equals("T")) {
                String description = txtsplit[2];
                Task todo = new Todo(description);
                if (txtsplit[1].equals("1")) {
                    todo.setDone();
                }
                arrlist.add(todo);
            } else if (txtsplit[0].equals("E")) {
                String description = txtsplit[2];
                String at = txtsplit[3];
                Task event = new Event(description, at);
                if (txtsplit[1].equals("1")) {
                    event.setDone();
                }
                arrlist.add(event);
            } else {
                String description = txtsplit[2];
                String by = txtsplit[3];
                Task deadline = new Deadline(description, by);
                if (txtsplit[1].equals("1")) {
                    deadline.setDone();;
                }
                arrlist.add(deadline);
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(textToAdd);
        writer.close();
    }

    private static void addtask() {
        String file = "data/task.txt";
        StringBuilder sb = new StringBuilder();

        try {
            for (int i = 0; i < arrlist.size(); i++) {
                if (i == arrlist.size() - 1) {
                    sb.append(arrlist.get(i).toStringintxt());
                } else {
                    sb.append(arrlist.get(i).toStringintxt());
                    sb.append("\n");
                }
            }
            String text = sb.toString();
            writeToFile(file, text);
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }


    public static void main(String[] args) {

        File file = new File("data");
        file.mkdir();
        try {
            loadtxt("data/task.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        drawline();
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");
        drawline();
        while(flag){
            String s = sc.nextLine();
            String [] a = s.split(" ");
            switch (a[0]) {
                case "bye" :
                    bye();
                    flag = false;
                    break;
                case "list" :
                    list(arrlist);
                    break;
                case "done":
                    int n = Integer.parseInt(a[1]);
                    done(arrlist, n-1);
                    break;
                case "todo":
                    try{
                        todo(arrlist,s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "event":
                    try{
                        event(arrlist,s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "deadline":

                    try{
                        deadline(arrlist,s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    int m = Integer.parseInt(a[1]);
                    delete(arrlist, m-1);
                    break;


                default:
                    try{
                        other(s);
                    }
                    catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

            }
        }
    }

    public static void drawline(){
        System.out.println("    ____________________________________________________________");
    }
    public static void bye(){
        drawline();
        System.out.println("     Bye. Hope to see you again soon!");
        drawline();

    }
    public static void list(ArrayList<Task>list){
        drawline();
        System.out.println("     Here are the tasks in your list:");
        for(int n=0; n<list.size();n++) {
            int m = n+1;
            System.out.println("     "+m+"."+list.get(n));
        }
        drawline();
    }
    public static void echo(ArrayList<Task>list,Task t){
        list.add(t);
        drawline();
        System.out.println("     added: "+t.getName());
        drawline();
    }
    public static void done(ArrayList<Task>list,int k){
        drawline();
        list.get(k).setDone();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     [✓]"+list.get(k).getName());
        drawline();
        addtask();
    }
    public static void todo(ArrayList<Task>list,String s) throws ErrorException{
        if (s.length() == 4) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            String td = s.substring(5);
            Task t = new Todo(td);
            list.add(t);
            drawline();
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + t);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
            drawline();
            addtask();
        }
    }
    public static void deadline(ArrayList<Task>list,String s) throws ErrorException{
        if (s.length() == 8) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            int sIndex = s.indexOf("/");
            int firstIndex = sIndex - 1;
            int secondIndex = sIndex + 4;
            String deadlineDescription = s.substring(9, firstIndex);
            String deadlineBy = s.substring(secondIndex);
            Task t = new Deadline(deadlineDescription, deadlineBy);
            list.add(t);
            drawline();
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + t);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
            drawline();
            addtask();
        }
    }
    public static void event(ArrayList<Task>list,String s) throws ErrorException{
        if (s.length() == 5) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else {
            int sIndex = s.indexOf("/");
            int firstIndex = sIndex - 1;
            int secondIndex = sIndex + 4;
            String eventDescription = s.substring(6, firstIndex);
            String eventAt = s.substring(secondIndex);
            Task t = new Event(eventDescription, eventAt);
            list.add(t);
            drawline();
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + t);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
            drawline();
            addtask();
        }
    }
    public static void other(String s) throws ErrorException{
        throw new ErrorException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    public static void delete(ArrayList<Task>list,int p){
        Task rt = list.get(p);
        drawline();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("     "+rt);
        list.remove(p);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
        drawline();
        addtask();
    }

}
