import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "data/tasks.txt";

    /**
     * Sets up the required objects, loads up the data from the storage file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            File data = new File("data");
            data.mkdir();
            File text = new File("data/tasks.txt");
            text.createNewFile();
            tasks = new TaskList(storage.loadtxt());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void start(Stage stage) {
        new Duke().run();
    }

    /**
     * Prints the welcome message,and scan user input.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            assert s.length() > 0 : "no new input";
            String[] a = Parser.parse(s);
            assert a.length > 0 : "input wrong";
            switch (a[0]) {
                case "bye":
                    ui.bye();
                    return;
                case "list":
                    list(tasks.getlist());
                    break;
                case "done":
                    int n = Integer.parseInt(a[1]);
                    done(tasks.getlist(), n - 1);
                    break;
                case "todo":
                    try {
                        todo(tasks.getlist(), s);
                    } catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "event":
                    try {
                        event(s);
                    } catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        deadline(s);
                    } catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    int m = Integer.parseInt(a[1]);
                    delete(tasks.getlist(), m - 1);
                    break;
                case "find":
                    find(tasks.getlist(), s);
                    break;
                case "stats":
                    stats(tasks.getlist());
                    break;

                default:
                    try {
                        other(s);
                    } catch (ErrorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @param s user command
     * @return result of the command
     */
    public void todo(ArrayList<Task> list, String s) throws ErrorException {
        if (s.length() == 4) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            String td = s.substring(5);
            Task t = new Todo(td);
            list.add(t);
            ui.drawline();
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + t);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
            ui.drawline();
            storage.addtask(tasks.getlist());
        }
    }

    public String todoString(ArrayList<Task> list, String s) throws ErrorException {
        if (s.length() == 4) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            String td = s.substring(5);
            Task t = new Todo(td);
            list.add(t);
            ui.drawline();
            String first = "Got it. I've added this task:\n";
            String second = ""+t;
            String third = "\nNow you have " + list.size() + " tasks in the list.";
            ui.drawline();
            storage.addtask(tasks.getlist());
            return first + second + third;
        }
    }


    /**
     * Executes the command and returns the result.
     *
     * @param s user command
     * @return result of the command
     */
    public void deadline(String s) throws ErrorException {
        if (s.length() == 8) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            int sIndex = s.indexOf("/");
            int firstIndex = sIndex - 1;
            int secondIndex = sIndex + 4;
            String deadlineDescription = s.substring(9, firstIndex);
            String deadlineBy = s.substring(secondIndex);
            Deadline d = new Deadline(deadlineDescription, deadlineBy);
            tasks.timeform(deadlineBy, d);
        }
    }

    public String deadlineString(String s) throws ErrorException {
        if (s.length() == 8) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            int sIndex = s.indexOf("/");
            int firstIndex = sIndex - 1;
            int secondIndex = sIndex + 4;
            String deadlineDescription = s.substring(9, firstIndex);
            String deadlineBy = s.substring(secondIndex);
            Deadline d = new Deadline(deadlineDescription, deadlineBy);
            return tasks.timeformString(deadlineBy, d);
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @param s user command
     * @return result of the command
     */
    public void event(String s) throws ErrorException {
        if (s.length() == 5) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            int sIndex = s.indexOf("/");
            int firstIndex = sIndex - 1;
            int secondIndex = sIndex + 4;
            String eventDescription = s.substring(6, firstIndex);
            String eventAt = s.substring(secondIndex);
            Event event = new Event(eventDescription, eventAt);
            tasks.timeform(eventAt, event);
        }
    }

    public String eventString(String s) throws ErrorException {
        if (s.length() == 5) {
            throw new ErrorException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            int sIndex = s.indexOf("/");
            int firstIndex = sIndex - 1;
            int secondIndex = sIndex + 4;
            String eventDescription = s.substring(6, firstIndex);
            String eventAt = s.substring(secondIndex);
            Event event = new Event(eventDescription, eventAt);
            return tasks.timeformString(eventAt, event);
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @param s user command
     * @return result of the command
     */
    public void other(String s) throws ErrorException {
        throw new ErrorException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public String otherString(String s) throws ErrorException {
        throw new ErrorException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void stats(ArrayList<Task> list) {
        ui.drawline();
        int n = list.size();
        System.out.println("     There are Total " + n + " tasks in the task list");
        int a = 0;
        int b = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatusIcon() == "\u2713") {
                a++;
            } else {
                b++;
            }
        }
        System.out.println("     " + a + " events have done");
        System.out.println("     " + b + " events have not GIT done");
        ui.drawline();
    }

    public String statsString(ArrayList<Task> list) {
        return "stats";
    }

    /**
     * Executes the command and returns the result.
     *
     * @param p user command
     * @return result of the command
     */

    public void delete(ArrayList<Task> list, int p) {
        Task rt = list.get(p);
        ui.drawline();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("     " + rt);
        list.remove(p);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
        ui.drawline();
        storage.addtask(tasks.getlist());
    }

    public String deleteString(ArrayList<Task> list, int p) {
        Task rt = list.get(p);

        String first = "     Noted. I've removed this task:";
        String second = "     " + rt;
        list.remove(p);
        String third = "     Now you have " + list.size() + " tasks in the list.";

        storage.addtask(tasks.getlist());
        return first + second + third;
    }

    /**
     * Executes the command and returns the result.
     */
    public void list(ArrayList<Task> list) {
        ui.drawline();
        System.out.println("     Here are the tasks in your list:");
        tasks.showlist();
        ui.drawline();
    }


    /**
     * Executes the command and returns the result.
     *
     * @param k user command
     * @return result of the command
     */
    public void done(ArrayList<Task> list, int k) {
        ui.drawline();
        list.get(k).setDone();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     [✓]" + list.get(k).getName());
        ui.drawline();
        storage.addtask(tasks.getlist());
    }

    public String doneString(ArrayList<Task> list, int k) {

        list.get(k).setDone();
        String first = ("     Nice! I've marked this task as done:");
        String second = ("     [✓]" + list.get(k).getName());

        storage.addtask(tasks.getlist());
        return first + second;
    }

    public void find(ArrayList<Task> list, String s) {
        String td = s.substring(5);
        ArrayList<Task> l = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(td) == true) {
                l.add(list.get(i));
            }
        }
        for (int i = 0; i < l.size(); i++) {
            int itemIndex = i + 1;
            String itemDisplay = itemIndex + "." + l.get(i).toString();
            System.out.println(itemDisplay);
        }
    }

    public String findString(ArrayList<Task> list, String s) {
        String td = s.substring(5);
        ArrayList<Task> l = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(td) == true) {
                l.add(list.get(i));
            }
        }
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            int n = i + 1;
            output = output + n + "." + list.get(i).toString() + "\n";
        }
        return output;
    }

    public String getResponse(String input) {
        String[] a = Parser.parse(input);
        switch (a[0]) {
            case "list":
                return tasks.showlistString();
            case "bye":
                Platform.exit();
                return "Bye. Hope to see you again soon!";
            case "todo":
                try {
                    return todoString(tasks.getlist(), input);
                } catch (ErrorException e) {
                    return e.getMessage();
                }
            case "done":
                    int n = Integer.parseInt(a[1]);
                    return doneString(tasks.getlist(), n - 1);

            case "event":
                    try {
                       return eventString(input);
                    } catch (ErrorException e) {
                        return e.getMessage();
                    }

            case "deadline":
                    try {
                       return deadlineString(input);
                    } catch (ErrorException e) {
                        return e.getMessage();
                    }

            case "delete":
                int m = Integer.parseInt(a[1]);
                return deleteString(tasks.getlist(), m - 1);

            case "find":
                   return findString(tasks.getlist(), input);

            case "stats":
                return statsString(tasks.getlist());

            default:
                try {
                       return otherString(input);
                    } catch (ErrorException e) {
                        return e.getMessage();
                    }

        }
    }
}

//    public String getResponse(String input) {
//        String output = "";
//        //ui.showWelcome();
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLine()) {
//            String s = sc.nextLine();
//            String[] a = Parser.parse(s);
//            switch (a[0]) {
//                case "bye":
//                    ui.bye();
//                    output = "";
//                case "list":
//                    String first = "     Here are the tasks in your list:";
//                    String second = tasks.showlistString();
//                    output = first + second;
//
//                case "done":
//                    int n = Integer.parseInt(a[1]);
//                    output= doneString(tasks.getlist(), n - 1);
//
//                case "todo":
//                    try {
//                        output = todoStrig(tasks.getlist(), s);
//                    } catch (ErrorException e) {
//                        output= e.getMessage();
//                    }
//
//                case "event":
//                    try {
//                       output= eventString(tasks.getlist(), s);
//                    } catch (ErrorException e) {
//                        output= e.getMessage();
//                    }
//
//
//                case "deadline":
//
//                    try {
//                       output= deadlineString(tasks.getlist(), s);
//                    } catch (ErrorException e) {
//                        output= e.getMessage();
//                    }
//
//                case "delete":
//                    int m = Integer.parseInt(a[1]);
//                   output= deleteString(tasks.getlist(), m - 1);
//
//                case "find":
//                   output= findString(tasks.getlist(), s);
//
//                default:
//                    try {
//                       output= otherString(s);
//                    } catch (ErrorException e) {
//                        output= e.getMessage();
//                    }
//            }
//        }
//        return output;


